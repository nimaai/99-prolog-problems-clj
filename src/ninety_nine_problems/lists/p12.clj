(ns ninety-nine-problems.lists.p12
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defna decodeo
    "l2 is the uncompressed version of the run-length encoded list l1." 
    [l1 l2]
    ([[] []])
    ([[[1 x] . ys] [x . zs]] (decodeo ys zs))
    ([[[n x] . ys] [x . zs]]
     (l/fresh [n1 ys1]
       (la/> n 1)
       (l/is n1 n dec)
       (l/conso [n1 x] ys ys1)
       (decodeo ys1 zs)))
    ([[x . ys] [x . zs]] (decodeo ys zs)))

  (t/is (= (l/run* [q] (decodeo '[[4,a],[1,b],[2,c],[2,a],[1,d],[4,e]] q))
           '([a,a,a,a,b,c,c,a,a,d,e,e,e,e]))))

(comment (t/run-tests))
