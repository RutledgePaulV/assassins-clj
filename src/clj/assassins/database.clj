(ns assassins.database
  (:require [datomic.api :as d]))


(def db-uri "datomic:free://docker:4334/assassins")
(d/create-database db-uri)
(def conn (d/connect db-uri))