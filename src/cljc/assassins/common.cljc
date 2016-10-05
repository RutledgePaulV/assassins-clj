(ns assassins.common)

(defn rotate [n coll]
  (let [size (count coll) offset (if (> n 0) (- size n) (* -1 n))]
    (take size (drop offset (cycle coll)))))

(defn circ-pairs [coll]
  (map vector coll (rotate -1 coll)))

