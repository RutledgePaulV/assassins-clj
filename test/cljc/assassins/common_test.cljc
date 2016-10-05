(ns assassins.common-test
  #?(:cljs (:require-macros [cljs.test :refer (is deftest testing)]))
  (:require [assassins.common :as common]
    #?(:clj
            [clojure.test :refer :all] :cljs [cljs.test])))

(deftest test-rotate
  (is (= [2 3 4 5 1] (into [] (common/rotate -1 [1 2 3 4 5]))))
  (is (= [5 1 2 3 4] (into [] (common/rotate 1 [1 2 3 4 5]))))
  (is (= [3 4 5 1 2] (into [] (common/rotate -2 [1 2 3 4 5]))))
  (is (= [4 5 1 2 3] (into [] (common/rotate 2 [1 2 3 4 5]))))
  (is (= [4 5 1 2 3] (into [] (common/rotate -3 [1 2 3 4 5]))))
  (is (= [3 4 5 1 2] (into [] (common/rotate 3 [1 2 3 4 5]))))
  (is (= [5 1 2 3 4] (into [] (common/rotate -4 [1 2 3 4 5]))))
  (is (= [2 3 4 5 1] (into [] (common/rotate 4 [1 2 3 4 5]))))
  (is (= [1 2 3 4 5] (into [] (common/rotate 5 [1 2 3 4 5]))))
  (is (= [1 2 3 4 5] (into [] (common/rotate 10 [1 2 3 4 5])))))

(deftest test-circ-pairs
  (is (= [[1 2] [2 3] [3 4] [4 5] [5 1]]
         (into [] (common/circ-pairs [1 2 3 4 5])))))