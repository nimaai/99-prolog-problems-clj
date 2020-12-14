(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

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
