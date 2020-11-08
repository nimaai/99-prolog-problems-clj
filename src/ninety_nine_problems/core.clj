(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== + - > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
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
  ([[] []])
  ([[x . xs] zs]
   (fresh [y ys]
     (flatteno x y)
     (flatteno xs ys)
     (appendo y ys zs)))
  ([x [x]]))

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

; P09
(defn packo [l1 l2]
  (letfn [(transfero [a b c d]
            (matche [a b c d]
              ([x [] [] [x]])
              ([x [y . ys] [y . ys] [x]] (!= x y))
              ([x [x . xs] ys [x . zs]] (transfero x xs ys zs))))]
    (matche [l1 l2]
      ([[] []])
      ([[x . xs] [z . zs]]
       (fresh [ys]
         (transfero x xs ys z)
         (packo ys zs))))))

; P10
(defn encodeo [l1 l2]
  (letfn [(transformo [l1 l2]
            (matche [l1 l2]
              ([[] []])
              ([[[x . xs] . ys] [[n x] . zs]]
               (fresh [p]
                 (conso x xs p)
                 (lengtho p n)
                 (transformo ys zs)))))]
    (fresh [p]
      (packo l1 p)
      (transformo p l2))))

; P11
(defn encode-modifiedo [l1 l2]
  (letfn [(stripo [l1 l2]
            (matche [l1 l2]
              ([[] []])
              ([[[1 x] . ys] [x . zs]] (stripo ys zs))
              ([[[n x] . ys] [[n x] . zs]]
               (> n 1)
               (stripo ys zs))))]
    (fresh [l]
      (encodeo l1 l)
      (stripo l l2))))

; P12
(defna decodeo [l1 l2]
  ([[] []])
  ([[[1 x] . ys] [x . zs]] (decodeo ys zs))
  ([[[n x] . ys] [x . zs]]
   (fresh [n1 ys1]
     (> n 1)
     (is n1 n dec)
     (conso [n1 x] ys ys1)
     (decodeo ys1 zs)))
  ([[x . ys] [x . zs]] (decodeo ys zs)))

; P13
(defn encode-directo [l1 l2]
  (letfn [(counto [a b c d e]
            (matche [a b c d e]
              ([x [] [] 1 x])
              ([x [] [] n [n x]] (> n 1))
              ([x [y . ys] [y . ys] 1 x] (!= x y))
              ([x [y . ys] [y . ys] n [n x]] (> n 1) (!= x y))
              ([x [x . xs] ys k t] (fresh [k1]
                                     (is k1 k inc) 
                                     (counto x xs ys k1 t)))))]
    (matche [l1 l2]
      ([[] []])
      ([[x . xs] [z . zs]]
       (fresh [ys]
         (counto x xs ys 1 z)
         (encode-directo ys zs))))))

; P14
(defne duplio [l1 l2]
  ([[] []])
  ([[x . y] [x x . z]] (duplio y z)))

; P15
(defn n-duplio [l1 n l2]
  (letfn [(n-duplio-ho [l1 n l2 k]
            (matche [l1 n l2 k]
              ([[] _ [] _])
              ([[_ . xs] n ys 0] (n-duplio-ho xs n ys n))
              ([[x . xs] n [x . ys] k] (fresh [k1 xs1]
                                         (> k 0)
                                         (is k1 k dec)
                                         (conso x xs xs1)
                                         (n-duplio-ho xs1 n ys k1)))))]
    (n-duplio-ho l1 n l2 n)))

; P16
(defn dropo [l1 n l2]
  (letfn [(dropo-ho [l1 n l2 k]
            (matche [l1 n l2 k]
              ([[] _ [] _])
              ([[_ . xs] n ys 1] (dropo-ho xs n ys n))
              ([[x . xs] n [x . ys] k] (fresh [k1]
                                         (> k 1)
                                         (is k1 k dec)
                                         (dropo-ho xs n ys k1)))))]
    (dropo-ho l1 n l2 n)))

; P17
(defne splito [l n l1 l2]
  ([l 0 [] l])
  ([[x . xs] n [x . ys] zs]
   (fresh [n1]
     (> n 0)
     (is n1 n dec)
     (splito xs n1 ys zs))))

;-----------------------------------------------------------------------

(comment
  (run* [q r]
    (splito '[a,b,c,d,e,f,g,h,i,k] 3 q r)))
