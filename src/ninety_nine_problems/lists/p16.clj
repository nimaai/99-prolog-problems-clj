(ns ninety-nine-problems.lists.p16
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(l/defne dropo-helpero 
  "l2 is obtained from l1 by duplicating its leading
   element k times, all other elements n times."
  [l1 n l2 k]
  ([[] _ [] _])
  ([[_ . xs] n ys 1] (dropo-ho xs n ys n))
  ([[x . xs] n [x . ys] k] (l/fresh [k1]
                             (la/> k 1)
                             (l/is k1 k dec)
                             (dropo-ho xs n ys k1))))

(t/with-test
  (defn dropo
    "l2 is obtained from l1 by duplicating all elements n times."
    [l1 n l2]
    (dropo-helpero l1 n l2 n))

  (t/is (= (l/run* [q] (dropo '[a,b,c,d,e,f,g,h,i,k] 3 q))
           '([a,b,d,e,g,h,k]))))

(comment (t/run-tests))
