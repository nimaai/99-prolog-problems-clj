(ns ninety-nine-problems.lists.p17
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne splito
    "The list l1 contains the first n elements
     of the list l, the list l2 contains the remaining elements."
    [l n l1 l2]
    ([l 0 [] l])
    ([[x . xs] n [x . ys] zs]
     (l/fresh [n1]
       (la/> n 0)
       (l/is n1 n dec)
       (splito xs n1 ys zs))))

  (t/is (= (l/run* [l1 l2] (splito '[a,b,c,d,e,f,g,h,i,k] 3 l1 l2))
           '([[a,b,c],[d,e,f,g,h,i,k]]))))

(comment (t/run-tests))
