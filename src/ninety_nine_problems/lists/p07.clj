(ns ninety-nine-problems.lists.p07
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  ; Negation predicate `\+` in prolog makes a cut. That's why it is
  ; needed to use `defna`. By reordering the clauses accordingly one
  ; can do without an analogous predicate alltogether.
  (l/defna flatteno 
    "The list l2 is obtained from the list L1 by flattening; i.e.
    if an element of l1 is a list then it is replaced by its elements,
    recursively."
    [l1 l2]
    ([[] []])
    ([[x . xs] zs]
     (l/fresh [y ys]
       (flatteno x y)
       (flatteno xs ys)
       (l/appendo y ys zs)))
    ([x [x]]))

  (t/is (= (l/run* [q] (flatteno '[a,[b,[c,d],e]] q))
           '((a,b,c,d,e)))))

(comment (t/run-tests))
