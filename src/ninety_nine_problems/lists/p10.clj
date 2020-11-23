(ns ninety-nine-problems.lists.p10
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [ninety-nine-problems.lists.p04 :refer [lengtho]]
            [ninety-nine-problems.lists.p09 :refer [packo]]))

(t/with-test
  (defn encodeo
    "The list l2 is obtained from the list l1 by run-length
     encoding. Consecutive duplicates of elements are encoded as terms [n,e],
     where n is the number of duplicates of the element e."
    [l1 l2]
    (letfn [(transformo [l1 l2]
              (l/matche [l1 l2]
                ([[] []])
                ([[[x . xs] . ys] [[n x] . zs]]
                 (l/fresh [p]
                   (l/conso x xs p)
                   (lengtho p n)
                   (transformo ys zs)))))]
      (l/fresh [p]
        (packo l1 p)
        (transformo p l2))))

  (t/is (= (l/run* [q] (encodeo '[a,a,a,a,b,c,c,a,a,d,e,e,e,e] q))
           '([[4,a],[1,b],[2,c],[2,a],[1,d],[4,e]]))))

(comment (t/run-tests))
