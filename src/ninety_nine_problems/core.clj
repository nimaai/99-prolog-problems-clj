(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

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

; P18
(defne sliceo [l i k s]
  ([[x . _] 1 1 [x]])
  ([[x . xs] 1 k [x . ys]] (fresh [k1]
                             (> k 1)
                             (is k1 k dec)
                             (sliceo xs 1 k1 ys)))
  ([[_ . xs] i k ys] (fresh [i1 k1]
                       (> i 1)
                       (is i1 i dec)
                       (is k1 k dec)
                       (sliceo xs i1 k1 ys))))

; P19
(defn rotateo [l1 n l2]
  (letfn [(rotate-lefto [l1 n l2]
            (matche [l1 n l2]
              ([l 0 l])
              ([l1 n l2]
               (> n 0)
               (fresh [s1 s2]
                 (splito l1 n s1 s2)
                 (appendo s2 s1 l2)))))]
    (fresh [n1 nl1]
      (lengtho l1 nl1)
      (project [n nl1] (== n1 (mod n nl1)))
      (rotate-lefto l1 n1 l2))))

; P20
(defne remove-ato [x l n r]
  ([x [x . xs] 1 xs])
  ([x [y . ys] n [y . zs]] (fresh [n1]
                             (> n 1)
                             (is n1 n dec)
                             (remove-ato x ys n1 zs))))

; P21
(defne insert-ato [x l n r]
  ([x xs 1 [x . xs]])
  ([x [y . ys] n [y . zs]] (fresh [n1]
                             (> n 1)
                             (is n1 n dec)
                             (insert-ato x ys n1 zs))))

; P22
(defne rangeo [n m l]
  ([n n [n]])
  ([n m [n . xs]] (fresh [n1]
                    (< n m)
                    (is n1 n inc)
                    (rangeo n1 m xs))))

; P23
(defne rnd-selecto [l n r]
  ([_ 0 []])
  ([xs n [x . zs]] (fresh [l i ys n1]
                     (> n 0)
                     (lengtho xs l)
                     (is i l #(-> % rand-int (+ 1)))
                     (remove-ato x xs i ys)
                     (is n1 n dec)
                     (rnd-selecto ys n1 zs))))

; P24
(defn lottoo [n m l]
  (fresh [r]
    (rangeo 1 m r)
    (rnd-selecto r n l)))

; P25
(defn rand-permuo [x y]
  (fresh [l]
    (lengtho x l)
    (rnd-selecto x l y)))

;-----------------------------------------------------------------------

(comment
  (run* [q]
    (rand-permuo '[a b c d e f] q)))
