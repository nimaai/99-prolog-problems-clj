(ns ninety-nine-problems.lists.p09
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(l/defne transfero 
  "ys is the list that remains from the list xs
   when all leading copies of x are removed and transfered to z."
  [a b c d]
  ([x [] [] [x]])
  ([x [y . ys] [y . ys] [x]] (l/!= x y))
  ([x [x . xs] ys [x . zs]] (transfero x xs ys zs))))

(t/with-test
  (defn packo
    "The list l2 is obtained from the list l1 by packing
     repeated occurrences of elements into separate sublists."
    [l1 l2]
    ([[] []])
    ([[x . xs] [z . zs]]
     (l/fresh [ys]
       (transfero x xs ys z)
       (packo ys zs))))

  (t/is (= (l/run* [q] (packo '[a,a,a,a,b,c,c,a,a,d,e,e,e,e] q))
           '([[a,a,a,a],[b],[c,c],[a,a],[d],[e,e,e,e]]))))

(comment (t/run-tests))
