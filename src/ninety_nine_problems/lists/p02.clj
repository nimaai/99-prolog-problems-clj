(ns ninety-nine-problems.lists.p02
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (defne last-but-one 
    "x is the last but one element of the list l." 
    [x y]
    ([x [x . [_]]])
    ([x [_ . r]] (last-but-one x r)))

  (t/is (= (l/run* [q] (last-but-one q '[a b c d]))
           '(c))))

(comment (t/run-tests))
