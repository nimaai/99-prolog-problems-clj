(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

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
