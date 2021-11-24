package com.github.hannotify.patternmatching.musicstore;

import com.github.hannotify.patternmatching.musicstore.effects.*;
import com.github.hannotify.patternmatching.musicstore.guitars.Guitar;
import com.github.hannotify.patternmatching.musicstore.guitars.GuitarType;
import com.github.hannotify.patternmatching.musicstore.hardware.Amplifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EffectApplierTest {

    @ParameterizedTest
    @MethodSource("provideEffects")
    void testApplyForEffects(Effect effect, Guitar guitar, String expectedOutput) {
        assertThat(EffectApplier.apply(effect, guitar)).isEqualTo(expectedOutput);
    }

    @Test
    void testApplyForEffectLoop() {
        Effect effectLoop = new EffectLoop("StockLoop",
                Set.of(new Delay(100), new Reverb("StadiumReverb", 10_000)));
        assertThat(EffectApplier.apply(effectLoop, provideGuitar())).contains(
                "Delay active of 100 ms.",
                "Reverb active of type StadiumReverb and roomSize 10000."
        );
    }

    private static Stream<Arguments> provideEffects() {
        var ibanez = provideGuitar();

        return Stream.of(
                Arguments.of(new Delay(40),
                        ibanez,
                        "Delay active of 40 ms."),
                Arguments.of(new Reverb("HallReverb", 30),
                        ibanez,
                        "Reverb active of type HallReverb and roomSize 30."),
                Arguments.of(new Overdrive(5),
                        ibanez,
                        "Overdrive active with gain 5."),
                Arguments.of(new Tremolo(8, 20),
                        ibanez,
                        "Tremolo active with depth 8 and rate 20."),
                Arguments.of(new Tuner(440),
                        ibanez,
                        "Tuner active with pitch 440. Muting all signal!")
        );
    }

    private static Guitar provideGuitar() {
        return new Guitar("Ibanez AZ224F", GuitarType.STRATOCASTER, new Amplifier("BOSS Katana 100"));
    }
}