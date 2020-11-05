(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== + -])
  (:use clojure.core.logic)
  (:require [clojure.core.logic.fd :refer [+ -]]))

; P01
(defne lasto [x y]
  ([x [x]])
  ([x [_ . r]] (lasto x r)))

; P02
(defne butlasto [x y]
  ([x [x . [_]]])
  ([x [_ . r]] (butlasto x r)))

; P03
(defne element-ato [x y n]
  ([_ [] _] fail)
  ([x [x . _] 1])
  ([x [_ . r] n]
   (fresh [n1]
     (is n1 n dec)
     (element-ato x r n1))))

; P04
(defne lengtho [l n]
  ([[] 0])
  ([[_ . r] n]
   (fresh [n1]
     (- n 1 n1)
     (lengtho r n1))))

; P05
(defne reverseo [l1 l2]
  ([[] []])
  ([[h . t] r]
   (fresh [rt]
     (reverseo t rt)
     (appendo rt [h] r))))

; P06
(defne palindromeo [l]
  [[_] (reverseo l l)])

; P07
(defna flatteno [l1 l2]
  ([[x . xs] zs]
   (fresh [y ys]
     (flatteno x y)
     (flatteno xs ys)
     (appendo y ys zs)))
  ([x [x]] (pred x (comp not seq?)))
  ([[] []]))

; P08
(defne compresso [l c]
  ([[] []])
  ([[x] [x]])
  ([[x x . xs] zs] (fresh [out]
                     (conso x xs out)
                     (compresso out zs)))
  ([[x y . ys] [x . zs]] (fresh [out]
                           (!= x y)
                           (conso y ys out)
                           (compresso out zs))))

(comment
  (run* [q]
    (compresso [1 1 1 2 3 3 4] q)
    )
  )
