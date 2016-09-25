(defproject assassins "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.89" :scope "provided"]
                 [com.cognitect/transit-clj "0.8.288"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [bk/ring-gzip "0.1.1"]
                 [ring-logger-timbre "0.7.5"]
                 [compojure "1.5.1"]
                 [environ "1.1.0"]
                 [reagent "0.6.0"]]

  :aot :all
  :plugins [[lein-cljsbuild "1.1.3"] [lein-environ "1.0.3"]]
  :aliases {"prod" ["with-profile" "-dev,+production" "uberjar"]}
  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj" "test/cljc"]
  :clean-targets ^{:protect false} [:target-path :compile-path "resources/public/js"]
  :uberjar-name "assassins.jar"
  :main assassins.server
  :repl-options {:init-ns user}
  :cljsbuild {:builds
              [{:id           "app"
                :source-paths ["src/cljs" "src/cljc"]
                :figwheel     true
                :compiler     {:main                 assassins.core
                               :asset-path           "js/compiled/out"
                               :output-to            "resources/public/js/compiled/assassins.js"
                               :output-dir           "resources/public/js/compiled/out"
                               :source-map-timestamp true}}

               {:id           "test"
                :source-paths ["src/cljs" "test/cljs" "src/cljc" "test/cljc"]
                :compiler     {:output-to     "resources/public/js/compiled/testable.js"
                               :main          assassins.test-runner
                               :optimizations :none}}

               {:id           "min"
                :source-paths ["src/cljs" "src/cljc"]
                :jar          true
                :compiler     {:main                 assassins.core
                               :output-to            "resources/public/js/compiled/assassins.js"
                               :output-dir           "target"
                               :source-map-timestamp true
                               :optimizations        :advanced
                               :pretty-print         false}}]}

  :figwheel {:css-dirs       ["resources/public/css"]
             :ring-handler   user/http-handler
             :server-logfile "log/figwheel.log"}

  :doo {:build "test"}

  :profiles {:dev
             {:dependencies [[figwheel "0.5.4-4"]
                             [figwheel-sidecar "0.5.4-4"]
                             [com.cemerick/piggieback "0.2.1"]
                             [org.clojure/tools.nrepl "0.2.12"]]

              :plugins      [[lein-figwheel "0.5.4-4"] [lein-doo "0.1.6"]]
              :source-paths ["dev"]
              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}

             :uberjar
             {:source-paths ^:replace ["src/clj" "src/cljc"]
              :prep-tasks   ["compile" ["cljsbuild" "once" "min"]]
              :hooks        []
              :omit-source  true
              :aot          :all}})
