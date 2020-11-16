(ns ninety-nine-problems.lists.p08
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne compresso 
    "The list l2 is obtained from the list l1 by
     compressing repeated occurrences of elements into a single copy
     of the element."
    [l c]
    ([[] []])
    ([[x] [x]])
    ([[x x . xs] zs] (l/fresh [out]
                       (l/conso x xs out)
                       (compresso out zs)))
    ([[x y . ys] [x . zs]] (l/fresh [out]
                             (l/!= x y)
                             (l/conso y ys out)
                             (compresso out zs))))

  (t/is (= (l/run* [q] (compresso '[a,a,a,a,b,c,c,a,a,d,e,e,e,e] q))
           '((a,b,c,a,d,e)))))

(comment (t/run-tests))
