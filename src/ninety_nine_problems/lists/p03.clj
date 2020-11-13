(ns ninety-nine-problems.lists.p03
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne element-ato
    "x is the k'th element of the list l."
    [x y n]
    ([_ [] _] l/fail)
    ([x [x . _] 1])
    ([x [_ . r] n]
     (l/fresh [n1]
       (l/is n1 n dec)
       (element-ato x r n1))))

  (t/is (= (l/run* [q] (element-ato q '[a b c d e] 3))
           '(c))))

(comment (t/run-tests))
