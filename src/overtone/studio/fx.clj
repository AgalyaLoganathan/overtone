(ns
  ^{:doc "Audio effects library"
     :author "Jeff Rose"}
  overtone.studio.fx
  (:use
    [overtone event]
    [overtone.sc synth ugen]))

(defsynth fx-noise-gate [in-bus 10 out-bus 0 threshold 0.4
                         slope-below 1 slope-above 0.1
                         clamp-time 0.01 relax-time 0.1]
  (let [source (in in-bus)]
    (out out-bus
         (compander source source threshold
                    slope-below slope-above
                    clamp-time relax-time))))

(defsynth fx-compressor [in-bus 10 out-bus 0 threshold 0.2
                         slope-below 1 slope-above 0.5
                         clamp-time 0.01 relax-time 0.01]
  (let [source (in in-bus)]
    (out out-bus
         (compander source source threshold
                    slope-below slope-above
                    clamp-time relax-time))))

(defsynth fx-limiter [in-bus 10 out-bus 0 threshold 0.2
                      slope-below 1 slope-above 0.1
                      clamp-time 0.01 relax-time 0.01]
  (let [source (in in-bus)]
    (out out-bus
         (compander source source threshold
                    slope-below slope-above
                    clamp-time relax-time))))

(defsynth fx-sustainer [in-bus 10 out-bus 0 threshold 0.2
                        slope-below 1 slope-above 0.5
                        clamp-time 0.01 relax-time 0.01]
  (let [source (in in-bus)]
    (out out-bus
         (compander source source threshold
                    slope-below slope-above
                    clamp-time relax-time))))

(defsynth fx-reverb [in-bus 10 out-bus 0
                     wet-dry 0.5 room-size 0.5 dampening 0.5]
  (out out-bus
       (free-verb (in in-bus) wet-dry room-size dampening)))

(defsynth fx-echo [in-bus 10 out-bus 0
                   max-delay 0.5 delay-time 0.2 decay-time 2.0]
  (let [source (in in-bus)
        echo (comb-n source max-delay delay-time decay-time)]
    (out out-bus (pan2 (+ echo source) 0))))

(defsynth fx-chorus [in-bus 10 out-bus 0 rate 0.002 depth 0.01]
  (let [src (in in-bus)
        dub-depth (* 2 depth)
        rates [rate (+ rate 0.001)]
        osc (+ dub-depth (* dub-depth (sin-osc:kr rates)))
        dly-a (delay-l src 0.3 osc)
        sig (apply + src dly-a)]
    (out out-bus (* 0.3 sig))))
