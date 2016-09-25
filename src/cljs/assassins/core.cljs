(ns assassins.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))


(defn nav []
  [:nav.ui.menu
   [:a.header.item {:href "/"} "Home"]
   [:a.item {:href "/games"} "Games"]
   [:a.item {:href "/geek"} "Geek"]
   [:a.item {:href "/about"} "About"]
   [:a.item.right {:href "/profile"} "Profile"]])

(defn main []
  [:main.ui
   [:div {:style {:margin-top "30px"}}
    [:div.ui.two.column.centered.grid
     [:div.ui.row.segment.basic
      [:h2.ui.header.center "You ready killa?"]]]]])

(defn index []
  [:div
   [:div.header [nav]]
   [:div.body [main]]
   [:div.footer]])

(reagent/render [index] (js/document.getElementById "app"))
