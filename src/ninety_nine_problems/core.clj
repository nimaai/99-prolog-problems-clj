(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

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
