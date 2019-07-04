(ns quick-type.core-test
  (:require [clojure.test :refer :all]
            [quick-type.core :refer :all]
            [active.clojure.sum-type :as s]))

;; defines a record-type with constructor make-kaan, predicate kaan?
;; and accessors kaan-one and kaan-two
(define-type kaan one two)

;; defines a sum-type of the above kaan record and a new record simon
;; with constructor make-simon, predicate simon? and accessors as above.
;; Defines a new sum-type predicate kaan-s?
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
