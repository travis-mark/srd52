(ns tl.srd52-test
  (:require [clojure.test :refer :all]
            [tl.srd52 :refer :all]))

(deftest a-test
  (testing "Sample passing test."
    (is (= 1 1))))

(deftest test-ability-modifiers
  (testing "Compute modifier for ability score"
    (let [test-cases [[1 -5] [2 -4] [3 -4] [4 -3] [5 -3]
                      [6 -2] [7 -2] [8 -1] [9 -1] [10 0]
                      [11 0] [12 1] [13 1] [14 2] [15 2]
                      [16 3] [17 3] [18 4] [19 4] [20 5]
                      [21 5] [22 6] [23 6] [24 7] [25 7]
                      [26 8] [27 8] [28 9] [29 9] [30 10]]]
      (doseq [[score expected] test-cases]
        (is (= (ability-modifier score) expected))))))

(deftest test-level-1-hit-points
  (testing "Level 1 Hit Points by Class" 
    (let [test-cases [[{:class :barbarian :level 1 :constitution 18} 16]
                      [{:class :paladin :level 1 :constitution 10} 10]]]
        (doseq [[character expected] test-cases]
          (is (= (max-hit-points character) expected))))))

(deftest test-leveled-hit-points
  (testing "Hit Points from Level Gain"
    (let [test-cases [[{:class :barbarian :level 4 :constitution 16} 45]]]
      (doseq [[character expected] test-cases]
        (is (= (max-hit-points character) expected))))))