(ns ninety-nine-problems.lists.p13
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(l/defne counto 
  "ys is the list that remains from the list xs
   when all leading copies of x are removed. t is the term [n,x],
   where n is k plus the number of x's that can be removed from xs.
   In the case of n=1, t is x, instead of the term [1,x]."
  [a b c d e]
  ([x [] [] 1 x])
  ([x [] [] n [n x]] (la/> n 1))
  ([x [y . ys] [y . ys] 1 x] (l/!= x y))
  ([x [y . ys] [y . ys] n [n x]] (la/> n 1) (l/!= x y))
  ([x [x . xs] ys k t] (l/fresh [k1]
                         (l/is k1 k inc) 
                         (counto x xs ys k1 t))))
(t/with-test
  (l/defne encode-directo
    "The list l2 is obtained from the list l1 by 
     run-length encoding. Consecutive duplicates of elements are encoded 
     as terms [n,e], where n is the number of duplicates of the element e.
     However, if n equals 1 then the element is simply copied into the 
     output list."
    [l1 l2]
    ([[] []])
    ([[x . xs] [z . zs]]
     (l/fresh [ys]
       (counto x xs ys 1 z)
       (encode-directo ys zs))))

  (t/is (= (l/run* [q] (encode-directo '[a,a,a,a,b,c,c,a,a,d,e,e,e,e] q))
           '([[4,a],b,[2,c],[2,a],d,[4,e]]))))

(comment (t/run-tests))
