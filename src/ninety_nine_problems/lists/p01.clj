(ns ninety-nine-problems.lists.p01
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne lasto
    "x is the last element of the list l."
    [x l]
    ([x [x]])
    ([x [_ . r]] (lasto x r)))

  (t/is (= (l/run* [q] (lasto q '[a b c]))
           '(c))))

(comment (t/run-tests))
