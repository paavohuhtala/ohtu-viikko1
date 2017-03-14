package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void luoNegatiivinenVarasto() {
        Varasto negaVarasto = new Varasto(-1.0);

        // negatiivisten varastojen tilavuus on nolla
        assertEquals(0.0, negaVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void luoSaldollinenVarasto() {
        Varasto saldoVarasto = new Varasto(10.0, 9.0);

        assertEquals(10.0, saldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(9.0, saldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    @Ignore
    public void luoSaldollinenVarastoNegatiivisellaTilalla() {
        Varasto saldoVarasto = new Varasto(-4, 1.0);

        assertEquals(0.0, saldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, saldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoSaldollinenVarastoNegatiivisellaSaldolla() {
        Varasto saldoVarasto = new Varasto(1.0, -1.0);

        assertEquals(1.0, saldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, saldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoYlivuotavaVarasto() {
        Varasto saldoVarasto = new Varasto(8.0, 9.0);

        assertEquals(8.0, saldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(8.0, saldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoTilatonSaldollinenVarasto() {
        Varasto saldoVarasto = new Varasto(0.0, 1.0);

        assertEquals(0.0, saldoVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, saldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaNegatiivinenMaara() {
        varasto.lisaaVarastoon(10.0);
        varasto.lisaaVarastoon(-1);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void otaNegatiivinenMaara() {
        varasto.lisaaVarastoon(10.0);
        varasto.otaVarastosta(-1);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaYlitayteen() {
        varasto.lisaaVarastoon(10.0);
        varasto.lisaaVarastoon(10.0);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaEnemmanKuinOn() {
        varasto.lisaaVarastoon(10.0);
        double otettu = varasto.otaVarastosta(20.0);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10.0, otettu, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoMuunnos() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
}