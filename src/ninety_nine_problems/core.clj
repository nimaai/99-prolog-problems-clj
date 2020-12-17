(ns ninety-nine-problems.core
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

;-----------------------------------------------------------------------

(comment
  (run* [q]
    (rand-permuo '[a b c d e f] q)))
