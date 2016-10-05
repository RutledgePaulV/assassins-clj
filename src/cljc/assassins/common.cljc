(ns assassins.common)

(defn rotate [n coll]
  (take (count coll) (drop 1 (cycle coll))))

(defn circ-pairs [coll]
  (zipmap coll (rotate 1 coll)))

