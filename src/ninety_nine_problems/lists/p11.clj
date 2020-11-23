(ns ninety-nine-problems.lists.p11
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [ninety-nine-problems.lists.p10 :refer [encodeo]]))

(t/with-test
  (defn encode-modifiedo 
    "The list l2 is obtained from the list l1 by 
     run-length encoding. Consecutive duplicates of elements are encoded 
     as terms [n,e], where n is the number of duplicates of the element e.
     However, if n equals 1 then the element is simply copied into the 
     output list."
    [l1 l2]
    (letfn [(stripo [l1 l2]
              (l/matche [l1 l2]
                ([[] []])
                ([[[1 x] . ys] [x . zs]] (stripo ys zs))
                ([[[n x] . ys] [[n x] . zs]]
                 (la/> n 1)
                 (stripo ys zs))))]
      (l/fresh [l]
        (encodeo l1 l)
        (stripo l l2))))

  (t/is (= (l/run* [q] (encode-modifiedo '[a,a,a,a,b,c,c,a,a,d,e,e,e,e] q))
           '([[4,a],b,[2,c],[2,a],d,[4,e]]))))

(comment (t/run-tests))
