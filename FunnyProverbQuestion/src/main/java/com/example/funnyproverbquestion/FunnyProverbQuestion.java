package com.example.funnyproverbquestion;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunnyProverbQuestion {
    public static void main(String[] args) {
        String[] turkishProverbs = {
                "Damlaya damlaya göl olur",
                "Ağaç yaşken eğilir",
                "Bir elin nesi var, iki elin sesi var",
                "Sabır acıdır, meyvesi tatlıdır",
                "Taşıma suyla değirmen dönmez",
                "Az veren candan, çok veren maldan",
                "Damdan düşenin halinden damdan düşen anlar",
                "Gülü seven dikenine katlanır",
                "Akıl akıldan üstündür",
                "Borç yiğidin kamçısıdır",
                "Dert paylaşıldıkça azalır",
                "Gözden uzak olan gönülden de uzak olur",
                "Hak yerini bulur",
                "Rüzgar eken fırtına biçer",
                "Tatlı dil yılanı deliğinden çıkarır",
                "Ucuz etin yahnisi yavan olur",
                "Varlık içinde darlık",
                "Zararın neresinden dönülse kardır",
                "Aslan yattığı yerden belli olur",
                "Can çıkmayınca huy çıkmaz",
                "İyi dost kara günde belli olur",
                "Bir lisan, bir insan; iki lisan, iki insan",
                "Sabah ola, hayır ola",
                "Su testisi su yolunda kırılır",
                "Yatan aslandan koşan tilki yeğdir",
                "Ateş olmayan yerden duman çıkmaz",
                "Balık baştan kokar",
                "Düşenin dostu olmaz",
                "Gülme komşuna, gelir başına",
                "Her şeyin yenisi, dostun eskisi makbuldür.",
                "Mart kapıdan baktırır, kazma kürek yaktırır",
                "Nerede birlik, orada dirlik",
                "Olmayacak duaya amin denmez",
                "Para parayı çeker",
                "Yaş kesen başı keser",
                "Zenginin malı, züğürdün çenesini yorar",
                "Açık ağız aç kalmaz",
                "Küçük suda büyük balık olmaz",
                "Bir musibet bin nasihatten iyidir",
                "Güneş balçıkla sıvanmaz",
                "Üzüm üzüme baka baka kararır",
                "Taş yerinde ağırdır",
                "Kaz gelen yerden tavuk esirgenmez",
                "Kedi uzanamadığı ciğerine mundar der",
                "Ayağını yorganına göre uzat",
        };

        String[][] turkishAntonyms = {
                {"sabah", "akşam"},
                {"ucuz", "pahalı"},
                {"sıcak", "soğuk"},
                {"büyük", "küçük"},
                {"aç", "tok"},
                {"güçlü", "zayıf"},
                {"hızlı", "yavaş"},
                {"zenginin", "fakirin"},
                {"dost", "düşman"},
                {"hayır", "evet"},
                {"yaş", "kuru"},
                {"acıdır", "tatlıdır"},
                {"dönmez", "döner"},
                {"aydınlık", "karanlık"},
                {"tatlı", "ekşi"},
                {"eski", "yeni"},
                {"mutlu", "mutsuz"},
                {"varlık", "yokluk"},
                {"uzak", "yakın"},
                {"kardır", "yağmurdur"},
                {"erken", "geç"},
                {"az", "çok"},
                {"sessiz", "gürültülü"},
                {"kapalı", "açık"},
                {"doğru", "yanlış"},
                {"ateş", "su"},
                {"dostu", "düşmanı"},
                {"giriş", "çıkış"},
                {"özgür", "tutsak"},
                {"güzel", "çirkin"},
                {"sağlam", "kırık"},
                {"aktif", "pasif"},
                {"bilge", "cahil"},
                {"yaşken", "kuruyken"},
                {"cesur", "korkak"},
                {"acıdır", "tatlıdır"},
                {"gülme", "ağlama"},
                {"veren", "alan"},
                {"düşenin", "kalkanın"},
                {"düşen", "kalkan"},
                {"gelen", "giden"},
                {"ağırdır", "hafiftir"},
                {"küçük", "büyük"},
                {"büyük", "küçük"},
                {"açık", "kapalı"},
                {"iyimser", "kötümser"},
                {"zor", "kolay"},
                {"esirgenmez", "esirgenir"},
                {"gelir", "gelmez"},
                {"ağır", "hafif"},
                {"esnek", "katı"},
                {"şanslı", "şanssız"},
                {"net", "belirsiz"},
                {"sabit", "değişken"},
                {"yeni", "eski"},
                {"dik", "yatay"},
                {"anlaşılır", "karmaşık"},
                {"doğal", "yapay"},
                {"mümkün", "imkansız"},
                {"bilinçli", "bilinçsiz"},
                {"eskisi", "yenisi"},
                {"yüksek", "alçak"},
                {"anlamlı", "anlamsız"},
                {"kesin", "muğlak"},
                {"dolu", "boş"},
                {"güvenli", "tehlikeli"},
                {"sıcakkanlı", "soğukkanlı"},
                {"akıllı", "aptal"},
                {"cömert", "cimri"},
                {"kısa", "uzun"},
                {"önden", "arkadan"},
                {"açık", "kapalı"},
                {"keskin", "körelmiş"},
                {"parlak", "sönük"},
                {"görünür", "görünmez"},
                {"tamamlanmış", "tamamlanmamış"},
                {"yaş", "kuru"},
                {"aktif", "inaktif"},
                {"batı", "doğu"},
                {"kuzey", "güney"},
                {"ilk", "son"},
                {"yaz", "kış"},
                {"ilkbahar", "sonbahar"},
                {"öncesi", "sonrası"},
                {"iç", "dış"},
                {"geniş", "dar"},
                {"kalın", "ince"},
                {"zengin", "yoksul"},
                {"sert", "yumuşak"},
                {"kazanç", "kayıp"},
                {"başarı", "başarısızlık"},
                {"mutlu", "üzgün"},
                {"gelişmiş", "geri kalmış"},
                {"tam", "eksik"},
                {"kalıcı", "geçici"},
                {"genç", "yaşlı"},
                {"yararlı", "zararlı"},
                {"kesin", "belirsiz"},
                {"anlayışlı", "anlayışsız"},
                {"doğru", "eğri"},
                {"tatlıdır", "ekşidir"},
                {"azalır", "çoğalır"}
        };

        String[][] changePairs =
                {
                        {"göl", "deniz"},
                        {"bir", "on"},
                        {"iki", "beş"},
                        {"taş", "dağ"},
                        {"su", "çöl"},
                        {"damdan", "pencereden"},
                        {"gülü", "papatyayı"},
                        {"akıl", "kalp"},
                        {"borç", "haraç"},
                        {"dert", "mutluluk"},
                        {"hak", "haksızlık"},
                        {"rüzgar", "tsunami"},
                        {"aslan", "penguen"},
                        {"can", "kemik"},
                        {"tilki", "kurt"},
                        {"balık", "balina"},
                        {"komşu", "ev sahibi"},
                        {"mart", "nisan"},
                        {"nerede", "kaçta"},
                        {"duaya", "ibadete"},
                        {"para", "fakirlik"},
                        {"ayağını", "elini"},
                        {"güneş", "ay"},
                        {"üzüm", "böğürtlen"},
                        {"çeker", "iter"},
                        {"tavuk", "ayı"},
                        {"kedi", "köpek"},
                        {"yorganına", "yatağına"},
                        {"kaz", "dinozor"},
                };

        Map<String, String> antonyms = new HashMap<>();
        for (String[] antonymPair : turkishAntonyms) {
            antonyms.put(antonymPair[0], antonymPair[1]);
        }

        Map<String, String> changes = new HashMap<>();
        for (String[] changePair : changePairs) {
            changes.put(changePair[0], changePair[1]);
        }

        for (String proverb : turkishProverbs) {
            String originalProverb = proverb.toLowerCase();
            System.out.println("Original Proverb: " + originalProverb);

            Set<String> changedWords = new HashSet<>();
            // İlk dönüşüm (antonimler) denemesi
            String modifiedProverb = applyTransformations(originalProverb, antonyms, changedWords, true);

            // Eğer antonimlerle değişiklik yapıldıysa, changePairs kontrol etmeden devam et
            if (changedWords.isEmpty()) {
                // İkinci dönüşüm (changePairs) sadece antonimlerle değişiklik yapılmadıysa
                modifiedProverb = applyTransformations(modifiedProverb, changes, changedWords, false);
            }

            String funnyQuestion = modifiedProverb + " " + determineQuestionSuffix(modifiedProverb) + "?";
            System.out.println("Funny Question: " + funnyQuestion + "\n");
        }
    }

    private static String applyTransformations(String text, Map<String, String> transformations, Set<String> changedWords, boolean checkChanged) {
        StringBuilder modifiedTextBuilder = new StringBuilder();
        String[] words = text.split("\\s+");
        for (String word : words) {
            // Eğer kelime daha önce değiştirildiyse ve kontrol etmemiz gerekiyorsa, atla
            if (checkChanged && changedWords.contains(word)) {
                modifiedTextBuilder.append(word).append(" ");
                continue;
            }
            String modifiedWord = word;
            for (Map.Entry<String, String> entry : transformations.entrySet()) {
                if (word.equals(entry.getKey())) {
                    modifiedWord = entry.getValue();
                    // Değişiklik yapıldıysa, bu kelimeyi kaydet
                    changedWords.add(word);
                    break;
                }
            }
            modifiedTextBuilder.append(modifiedWord).append(" ");
        }
        return modifiedTextBuilder.toString().trim();
    }

    private static String determineQuestionSuffix(String text) {
        char lastVowel = ' ';
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if ("aeıioöuü".indexOf(c) >= 0) {
                lastVowel = c;
                break;
            }
        }

        switch (lastVowel) {
            case 'a':
            case 'ı':
                return "mı";
            case 'o':
            case 'u':
                return "mu";
            case 'e':
            case 'i':
                return "mi";
            case 'ö':
            case 'ü':
                return "mü";
            default:
                return "mi";
        }
    }
}