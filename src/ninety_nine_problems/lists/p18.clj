(ns ninety-nine-problems.lists.p18
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne sliceo
    "l2 is the list of the elements of l1 between
     index i and index k (both included)."
    [l i k s]
    ([[x . _] 1 1 [x]])
    ([[x . xs] 1 k [x . ys]] (l/fresh [k1]
                               (la/> k 1)
                               (l/is k1 k dec)
                               (sliceo xs 1 k1 ys)))
    ([[_ . xs] i k ys] (l/fresh [i1 k1]
                         (la/> i 1)
                         (l/is i1 i dec)
                         (l/is k1 k dec)
                         (sliceo xs i1 k1 ys))))

  (t/is (= (l/run* [l] (sliceo '[a,b,c,d,e,f,g,h,i,k], 3, 7, l))
           '([c,d,e,f,g]))))

(comment (t/run-tests))
