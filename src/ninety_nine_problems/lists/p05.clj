(ns ninety-nine-problems.lists.p05
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne reverseo 
    "l2 is the list obtained from l1 by reversing the order of the elements."
    [l1 l2]
    ([[] []])
    ([[h . t] r]
     (l/fresh [rt]
       (reverseo t rt)
       (l/appendo rt [h] r))))

  (t/is (= (l/run* [q] (reverseo '[a b c d e] q))
           '((e d c b a)))))

(comment (t/run-tests))
