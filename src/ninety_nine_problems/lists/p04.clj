(ns ninety-nine-problems.lists.p04
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne lengtho
    "The list l contains n elements."
    [l n]
    ([[] 0])
    ([[_ . r] n] (l/fresh [n1]
                   (lengtho r n1)
                   (l/is n n1 inc))))

  (t/is (= (l/run* [q] (lengtho '[a b c d e] q))
           '(5))))

(comment (t/run-tests))
