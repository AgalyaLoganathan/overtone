(ns
    ^{:doc "Namespace containing all default ugens and cgens"
      :author "Sam Aaron"}
  overtone.sc.gens
  (:use [overtone.util.ns :only [immigrate]])
  (:require [overtone.sc.machinery.cgens oscillators demand mix io buf-io env]
            [overtone.sc.machinery ugens defcgen]))

(immigrate
 'overtone.sc.machinery.ugens
 'overtone.sc.machinery.defcgen
 'overtone.sc.machinery.cgens.oscillators
 'overtone.sc.machinery.cgens.demand
 'overtone.sc.machinery.cgens.mix
 'overtone.sc.machinery.cgens.io
 'overtone.sc.machinery.cgens.buf-io
 'overtone.sc.machinery.cgens.env)
