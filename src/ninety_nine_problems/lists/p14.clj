(ns ninety-nine-problems.lists.p14
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne duplio
    "l2 is obtained from l1 by duplicating all elements."
    [l1 l2]
    ([[] []])
    ([[x . y] [x x . z]] (duplio y z)))

  (t/is (= (l/run* [q] (duplio '[a,b,c,c,d] q))
           '([a,a,b,b,c,c,c,c,d,d]))))

(comment (t/run-tests))
