(ns quick-type.core-test
  (:require [clojure.test :refer :all]
            [quick-type.core :refer :all]
            [active.clojure.sum-type :as s]))

(define-type kaan one two)
(define-type kaan-s [(simon one two) kaan])

(deftest record-test
  (testing "record test"
    (is (= 1 (kaan-one (make-kaan 1 2))))
    (is (= 2 (kaan-two (make-kaan 1 2))))
    (is (kaan? (make-kaan 1 2)))))

(deftest sum-type-test
  (testing "sum type tests"
    (is (kaan-s? (make-kaan 1 2)))
    (is (kaan-s? (make-simon 1 2)))
    (is (= 12 (s/match kaan-s (make-simon 1 2)
                simon? 12
                kaan? 2
                )))))
