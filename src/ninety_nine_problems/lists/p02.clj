(ns ninety-nine-problems.lists.p02
  (:refer-clojure :exclude [== > < >= <= =])
  (:use clojure.core.logic
        clojure.core.logic.arithmetic)
  (:require [clojure.core.logic.fd :as fd]))

(defne butlasto "docu" [x y]
  ([x [x . [_]]])
  ([x [_ . r]] (butlasto x r)))
