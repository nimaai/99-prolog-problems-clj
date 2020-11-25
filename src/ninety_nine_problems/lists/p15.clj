(ns ninety-nine-problems.lists.p15
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(l/defne n-duplio-ho
  "l2 is obtained from l1 by duplicating its leading
   element k times, all other elements n times."
  [l1 n l2 k]
  ([[] _ [] _])
  ([[_ . xs] n ys 0] (n-duplio-ho xs n ys n))
  ([[x . xs] n [x . ys] k] (l/fresh [k1 xs1]
                             (la/> k 0)
                             (l/is k1 k dec)
                             (l/conso x xs xs1)
                             (n-duplio-ho xs1 n ys k1))))

(t/with-test
  (defn n-duplio
    "l2 is obtained from l1 by duplicating all elements n times."
    [l1 n l2]
    (n-duplio-ho l1 n l2 n))

  (t/is (= (l/run* [q] (n-duplio '[a,b,c] 3 q))
           '([a,a,a,b,b,b,c,c,c]))))

(comment (t/run-tests))
