(ns ninety-nine-problems.lists.p06
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [ninety-nine-problems.lists.p05 :refer [reverseo]]))

(t/with-test
  (defn palindromeo
    "A palindrome can be read forward or backward; e.g. [x,a,m,a,x]."
    [l]
    (reverseo l l))

  (t/is (= (l/run* [q] (palindromeo '[x,a,m,a,y]))
           '(_0))))

(comment (t/run-tests))
