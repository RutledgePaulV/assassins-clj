(ns assassins.core
  (:require [reagent.core :as reagent :refer [atom]]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as reagent])
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History))

(defn nav []
  [:nav.ui.menu
   [:a.header.item {:href "#"} "Home"]
   [:a.item {:href "#games"} "How It Works"]
   [:a.item {:href "#geek"} "Our Story"]
   [:a.item {:href "#about"} "Team"]
   [:a.item.right {:href "#profile"} "Profile"]])

(defn main []
  [:main.ui
   [:div {:style {:margin-top "30px"}}
    [:div.ui.two.column.centered.grid
     [:div.ui.row.segment.basic
      [:h2.ui.header.center "You ready?"]]]]])

(defn index []
  [:div
   [:div.header [nav]]
   [:div.body [main]]
   [:div.footer]])

(defn games []
  [:div
   [:div.header [nav]]
   [:div.body [:div "games"]]
   [:div.footer]])

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      #(secretary/dispatch! (.-token %)))
    (.setEnabled true)))

(enable-console-print!)
(hook-browser-navigation!)
(secretary/set-config! :prefix "#")
(defonce app-state (atom {}))

(defmulti current-page #(@app-state :page))
(defmethod current-page :home [] [index])
(defmethod current-page :games [] [games])
(defmethod current-page :default [] [index])
(defroute "/" [] (swap! app-state assoc :page :home))
(defroute "/games" [] (swap! app-state assoc :page :games))
(reagent/render [current-page] (.getElementById js/document "app"))

