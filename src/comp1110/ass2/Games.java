package comp1110.ass2;

public class Games {

    /**
     * All game challenges - levels are indexed as follows
     * Note that each level has 24 challenges, for a total of 120
     * Starter: indices 0 - 23
     * Junior: indices 24 - 47
     * Expert: indices 48 - 71
     * Master: indices 72 - 95
     * Wizard: indices 96 - 119
     */

    public static final String[] ALL_CHALLENGES = new String[] {
            "y322g262b100i010p340W", // Starter
            "r220y200g512b260p440W",
            "r101y250g000b020i013W",
            "r241o020y200b511i040W",
            "r030o010y400i033p202W",
            "o100y511g130i241p010W",
            "r240o302b121i260p242W",
            "r220y200g540b312p041W",
            "r101o232g262i000p231W",
            "o140y250g262b400p202W",
            "r042o100g110b450i020W",
            "r010o222g130b100i040W",
            "r101y040b420p151W",
            "y400b342i040p202W",
            "r022o302y000b030W",
            "r250o401b331i020W",
            "y521g110i040p020W",
            "r151g540i023p202W",
            "r020o100y352b312W",
            "o302y250g262i231W",
            "r010o302g262i100W",
            "r002y000b260p242W",
            "o302g262b400i040W",
            "r242g540b010p041W",
            "y002g020b040W", // Junior
            "r030b322i120W",
            "r010y250i231W",
            "r010y040p130W",
            "r020b400i040W",
            "r130y240b000W",
            "o151b332p202W",
            "r020o100y040W",
            "o302g262i010W",
            "r151g540b000W",
            "y040b000p301W",
            "o100b040i010W",
            "r002g252W",
            "o540g512W",
            "g000p341W",
            "o310b100W",
            "o321i260W",
            "g000i030W",
            "y322p331W",
            "g130p111W",
            "o540y000W",
            "g252p301W",
            "r130o140W",
            "b130i040W",
            "o222y040W", // Expert
            "g231i003W",
            "r240i010W",
            "i000p450W",
            "g311i032W",
            "b531p202W",
            "b342p011W",
            "r222g540W",
            "o262p121W",
            "r021i010W",
            "o401i033W",
            "o460g232W",
            "o302y342W",
            "r002p222W",
            "b450i010W",
            "o322p151W",
            "y030b000W",
            "r151y200W",
            "r252g232W",
            "y141b450W",
            "r212b450W",
            "r220i040W",
            "r030p301W",
            "o460i030W",
            "o522W", // Master
            "b011W",
            "g240W",
            "o240W",
            "y130W",
            "o520W",
            "r221W",
            "p411W",
            "p421W",
            "p320W",
            "g250W",
            "y421W",
            "b520W",
            "g521W",
            "i250W",
            "b021W",
            "p211W",
            "i131W",
            "o531W",
            "o211W",
            "y220W",
            "y230W",
            "b521W",
            "y032W",
            "Wr22o13b21", // Wizard
            "Wg11g32i00p43",
            "Wg11b13p31",
            "Wr10o30i00",
            "Wr43o42i33",
            "Wy50b30p10",
            "Wo32g31p30",
            "Wg62i30p02",
            "Wr62g22i42",
            "Wo53y13p31",
            "Wo12p21",
            "Wg01i23",
            "Wy13b43i30",
            "Wb32i21",
            "Wy53p00",
            "Wb01i51",
            "Wo21y31",
            "Wr40g20b60",
            "Wy50b21i43",
            "Wr40i12",
            "Wy22i23",
            "Wo52y32",
            "Wr30y23",
            "Wo40y20"
    };

    /**
     * Solutions to each challenge - same ordering as in ALL_CHALLENGES
     */

    public static final String[] ALL_CHALLENGES_SOLUTIONS = new String[] {
            "r250o011y322g262b100i010p340W",
            "r220o262y200g512b260i013p440W",
            "r101o262y250g000b020i013p221W",
            "r241o020y200g262b511i040p121W",
            "r030o010y400g532b450i033p202W",
            "r252o100y511g130b040i241p010W",
            "r240o302y010g300b121i260p242W",
            "r220o252y200g540b312i022p041W",
            "r101o232y040g262b230i000p231W",
            "r220o140y250g262b400i231p202W",
            "r042o100y521g110b450i020p431W",
            "r010o222y352g130b100i040p541W",
            "r101o331y040g000b420i013p151W",
            "r041o010y400g130b342i040p202W",
            "r022o302y000g511b030i260p242W",
            "r250o401y521g262b331i020p000W",
            "r041o100y521g110b342i040p020W",
            "r151o241y400g540b010i023p202W",
            "r020o100y352g310b312i040p541W",
            "r120o302y250g262b400i231p030W",
            "r010o302y250g262b231i100p030W",
            "r002o431y000g530b260i111p242W",
            "r241o302y221g262b400i040p420W",
            "r242o302y411g540b010i100p041W",
            "r000o341y002g020b040i033p121W",
            "r030o100y250g110b322i120p242W",
            "r010o222y250g262b100i231p030W",
            "r010o251y040g262b100i003p130W",
            "r020o302y341g311b400i040p151W",
            "r130o012y240g262b000i260p301W",
            "r010o151y040g330b332i100p202W",
            "r020o100y040g110b322i032p151W",
            "r121o302y400g262b260i010p440W",
            "r151o401y541g540b000i230p212W",
            "r151o012y040g321b000i130p301W",
            "r031o100y322g262b040i010p511W",
            "r002o300y331g252b010i040p041W",
            "r220o540y200g512b260i013p242W",
            "r101o540y560g000b420i013p341W",
            "r241o310y322g262b100i040p020W",
            "r240o321y200g262b312i260p420W",
            "r101o320y332g000b260i030p242W",
            "r201o260y322g530b042i000p331W",
            "r151o010y541g130b100i040p111W",
            "r002o540y000g262b260i111p330W",
            "r211o151y040g252b130i000p301W",
            "r130o140y250g262b312i000p501W",
            "r211o302y000g252b130i040p041W",
            "r201o222y040g330b121i000p151W",
            "r010o100y352g231b450i003p030W",
            "r240o302y560g300b342i010p011W",
            "r201o151y322g321b430i000p450W",
            "r020o302y040g311b400i032p151W",
            "r010o030y250g262b531i100p202W",
            "r240o302y560g300b342i010p011W",
            "r222o100y352g540b010i211p541W",
            "r101o262y250g000b020i241p121W",
            "r021o151y040g252b100i010p111W",
            "r031o401y020g000b450i033p212W",
            "r201o460y352g232b030i000p521W",
            "r001o302y342g031b450i000p430W",
            "r002o262y250g230b540i000p222W",
            "r140o302y352g300b450i010p011W",
            "r002o322y040g230b331i000p151W",
            "r012o401y030g262b000i260p221W",
            "r151o441y200g540b312i121p420W",
            "r252o221y200g232b040i241p520W",
            "r002o530y141g001b450i000p331W",
            "r212o501y342g031b450i000p430W",
            "r220o302y331g252b400i040p041W",
            "r030o042y111g321b450i000p301W",
            "r232o460y000g511b141i030p202W",
            "r101o522y130g252b000i040p041W",
            "r012o401y250g262b011i000p030W",
            "r032o151y322g240b100i010p450W",
            "r250o240y200g262b312i032p420W",
            "r101o522y130g252b000i040p041W",
            "r241o520y221g262b400i040p202W",
            "r221o501y250g262b312i000p030W",
            "r022o401y560g331b030i000p411W",
            "r002o300y341g262b040i010p421W",
            "r031o460y200g530b312i033p320W",
            "r222o401y560g250b342i020p000W",
            "r252o012y421g260b000i030p301W",
            "r242o222y200g130b520i040p041W",
            "r042o401y332g521b450i020p000W",
            "r101o460y352g000b020i250p121W",
            "r222o401y250g262b021i020p000W",
            "r002o260y000g530b042i023p211W",
            "r212o151y200g520b121i131p450W",
            "r012o531y560g530b342i000p401W",
            "r242o211y000g232b530i040p041W",
            "r101o000y220g540b032i013p041W",
            "r250o401y230g120b042i023p000W",
            "r151o401y040g020b521i023p000W",
            "r000o550y032g221b020i003p151W",
            "r001o302y040g330b121i000p151Wr22o13b21",
            "r012o401y030g011b260i000p242Wg11g32i00p43",
            "r242o151y040g501b312i000p130Wg11b13p31",
            "r010o030y250g262b531i100p202Wr10o30i00",
            "r252o541y200g540b312i121p420Wr43o42i33",
            "r151o401y040g120b130i023p000Wy50b30p10",
            "r041o321y200g540b312i033p420Wo32g31p30",
            "r031o121y400g262b040i010p202Wg62i30p02",
            "r151o401y040g120b332i130p000Wr62g22i42",
            "r250o262y322g011b100i010p340Wo53y13p31",
            "r031o512y200g520b450i033p121Wo12p21",
            "r010o302y352g300b450i231p030Wg01i23",
            "r041o100y322g540b342i010p511Wy13b43i30",
            "r151o401y040g020b332i221p000Wb32i21",
            "r020o401y441g522b040i023p000Wy53p00",
            "r121o302y240g262b400i260p520Wb01i51",
            "r012o321y030g262b260i000p401Wo21y31",
            "r030o042y400g010b450i023p202Wr40g20b60",
            "r151o401y040g020b521i023p000Wy50b21i43",
            "r030o100y250g010b322i211p242Wr40i12",
            "r101o000y220g540b032i013p041Wy22i23",
            "r101o031y530g242b000i040p151Wo52y32",
            "r030o042y111g321b450i000p301Wr30y23",
            "r002o540y000g262b260i111p330Wo40y20"
    };
}