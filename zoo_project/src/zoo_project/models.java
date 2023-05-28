package zoo_project;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import zoo_project.TicketInfo.TicketType;

public class models {
	Zoo zoo;

	public models() {
		zoo = new Zoo("神戶動物王國", "7 Chome-1-9 Minatojima Minamimachi, Chuo Ward, Kobe, Hyogo 650-0047日本",
				"+81783028899", "new_map.png");
		// ------------------------------------------------------------------------
		Zone entrance = new Zone("入口", "");
		Zone toilet = new Zone("廁所", "");
		Zone z0 = new Zone("水之谷",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/98A2722_stamp_R-700x433-380x235.jpg");
		Zone z1 = new Zone("袋鼠農場",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2021/07/98A3009_R-700x433-380x235.jpg");
		Zone z2 = new Zone("羊之丘", "https://tw.kobe-oukoku.com/wp-content/uploads/2016/05/1DX_1167-380x235.jpg");
		Zone z3 = new Zone("羊駝空間",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/98A2292_R-700x433-380x235.jpg");
		Zone z4 = new Zone("騎馬&騎駱駝",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/81521522f7257c9d5fb0222806269afd-2-700x433-380x235.jpg");
		Zone z5 = new Zone("水獺聖地", "1630838976_45787.jpg");
		Zone z6 = new Zone("松鼠森林", "LSeQYr4plG_small.jpg");
		zoo.addZone(Arrays.asList(z0, z1, z2, z3, z4, z5, z6));
		// ------------------------------------------------------------------------
		Linemap l0 = new Linemap(entrance, z0, "mapA.jpg");
		Linemap l1 = new Linemap(entrance, z1, "mapB.jpg");
		Linemap l2 = new Linemap(entrance, z2, "mapC.jpg");
		Linemap l3 = new Linemap(entrance, z3, "mapD.jpg");
		Linemap l4 = new Linemap(entrance, z4, "mapE.jpg");
		Linemap l5 = new Linemap(entrance, z5, "mapF.jpg");
		Linemap l6 = new Linemap(entrance, z6, "mapG.jpg");
		Linemap l7 = new Linemap(entrance, toilet, "mapToilet.jpg");
		zoo.addLinemaps(Arrays.asList(l0, l1, l2, l3, l4, l5, l6, l7));

		// ------------------------------------------------------------------------
		Animal a0 = new Animal("斑海豹",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2019/09/seal5-285x176.jpg",
				"斑海豹也叫大齒斑海豹、大齒海豹，是一種在在北半球的西北太平洋分布的海豹。斑海豹分布的西北太平洋的高緯度寒冷水域，主要分布在楚科奇海、白令海、鄂霍次克海、日本海和中國的渤海、黃海北部。斑海豹身體呈紡錘形，頭圓生有觸鬚，沒有外耳廓。四肢特化成鰭腳，趾間有蹼。雌性斑海豹體型大於雄性。出生的小海豹全身被白色的胎毛包裹。斑海豹每年都要換毛。它們有洄游的行為，食物主要為魚類和頭足類。3年以後達到性成熟，為一雄多雌生殖型，在每年的1~3月份繁殖，產仔在浮冰上，每胎產一仔。");
		Animal a1 = new Animal("南美海狗",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2019/09/fur-seal-285x176.jpg",
				"南美海狗，屬於動物界，脊索動物門，脊椎動物亞門。雄獸深灰，雌性和多數未成熟個體體色多樣，頸和背部多為灰色，但有些毛尖部白色，使其呈銀灰色，腹部淡黃。具外耳殼。雄獸體長1．89m，重159kg；雌性1.43m，48.5kg。雄獸深灰，雌性和多數未成熟個體體色多樣，頸和背部多為灰色，但有些毛尖部白色，使其呈銀灰色，腹部淡黃。具外耳殼。頭骨顱基長255mm，額部平，吻中長，鼻長38mm，齶部寬，齒列平行，齒冠三尖或單尖，齒式2156/315＝34～36。以無脊椎動物、上層魚、企鵝和頭足類為食。11月繁殖，1頭雄獸平均和3—5頭雌獸組成多雌群。仔獸重3.35～5.45kg。雌獸產後很快交尾。主要分布於福克蘭群島、南美沿岸從火地島向北到巴西的里約熱內盧和秘魯的利馬。估計福克蘭群島有15000～16000頭，南美有307000頭。");
		Animal a2 = new Animal("黑腳企鵝",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2019/09/penguin-285x176.jpg",
				"「黑腳企鵝」是唯一生活在非洲的企鵝，主要分布在納米比亞（Namibia）到南非共和國夸祖魯-納塔爾省（KwaZulu-Natal）的海邊，曾經是南非數量最多的海鳥。但這20年來，因為人類挖採鳥糞石、採收企鵝蛋食用、過度漁撈、觀光客行為及掠食者的引進（外來種）等因素，黑腳企鵝野外數量急速下降，加上時有所聞的「船隻漏油事件」，對近代搖搖欲墜的黑腳企鵝族群更是雪上加霜，因為漂浮在海上的油汙會讓企鵝的羽毛失去防水性而失溫、無法下海覓食餓死、或因為理毛時吞下油汙中毒等，就算能僥倖存活下來的個體，未來繁殖的成功率也會大幅降低，讓人不禁要感嘆，野生黑腳企鵝的命運實在是太坎坷了！");
		z0.addAnimal(Arrays.asList(a0, a1, a2));

		Animal a3 = new Animal("花鹿",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/e8501034796af022c4a2a487e9942046.jpg",
				"花鹿，別名斑鹿，白斑鹿（Axis axis），是屬於鹿科的一種，生活在斯里蘭卡，尼泊爾，孟加拉國，和印度的森林地帶。是印度森林中最常見的鹿種。背面淺紅褐色，具斑點，腹面白色。雄鹿生角，分三叉，可長大75厘米。由於花鹿產於熱帶，一整年都可以交配。肩高90厘米，體重可達85千克。壽命長約20-30年。喜群居，通常每群10-50頭，其中有一到兩隻是雄鹿。對人類的接近比較寬容，尤其是生活在受到人類影響的地區的。生活在低緯度的森林，在高緯度森林它們經常被其它的鹿種（水鹿等）替代。主要植物為食，但也會吃掉脫落掉的鹿茸。花鹿被傳入到澳大利亞昆士蘭州，美國加利福尼亞，德克薩斯，佛羅里達州和夏威夷群島。");
		Animal a4 = new Animal("紅大袋鼠",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/e3-82-a2-e3-82-ab-e3-82-ab-e3-83-b3-e3-82-ab-e3-82-99-e3-83-ab-e3-83-bc_-4-285x176.jpg",
				"紅大袋鼠是非常大的袋鼠，雄性有紅褐色的短毛，下身及四肢的毛色呈黃褐色。牠們的耳朵尖長，吻呈方形。雌性較雄性細小，呈藍灰色，下身呈淡灰色。在乾旱地區的雌性則較為像雄性。前肢有細小的爪，後肢粗壯適合跳躍，尾巴強壯可以幫助站立。牠們的腳像橡皮圈。它們可以跳3米高，9米遠。並能達到時速60公里以上。雄性紅大袋鼠身長1.4米及重85公斤。雌性身長0.9-1米。牠們站立時約高1.5米。紅大袋鼠會採取多種物理、生理及行為的適應性來保持體溫於約36℃，以達至體內平衡。這些包括保溫的短毛、降低活躍性、留在蔭下、及舔自己的前肢。由於眼睛的位置，紅大袋鼠的視覺約有300°。紅大袋鼠棲息在澳洲中部的乾旱內陸，以及較少樹生長的遼闊平原。");
		z1.addAnimal(Arrays.asList(a3, a4));

		Animal a5 = new Animal("迷你馬",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/05/IMG_9423_R-285x176.jpg",
				"迷你馬又稱袖珍馬系澳大利亞特產，體高60～100公分，平均高度80公分，體長100公分左右，體重50～100公斤。一歲左右發情即可配種。飼養方法與各種國產馬無異，不同之處是迷你馬身強體壯。迷你馬性情温順，聰明可愛且忠實可靠，深受青少年青睞。善於爬山涉水，步伐穩健靈活，耐力強，正常挽拽力為體重3倍，馱載為體重2/5、1/2，常步挽和馱載每小時4.5～5.5公里,乘騎常步每小時6～7公里。適應性強，耐渴耐勞、耐寒冷，耐組飼，易調價，繁殖率高，抗病能力強。粗飼、秸稈、青草為主食，很少補給精料。此外餵食豌豆、玉米等可以令其毛色鮮亮。");
		Animal a6 = new Animal("迷你豬",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2020/08/Miniature-Pig-285x176.jpg",
				"迷你豬即為麝香豬，麝香豬在豬家族中屬於“天資聰穎”一類，出生後就會用嗅覺辨別主人，對生人很是警惕。它的“眼神”不佳，嗅覺和聽覺十分出色，能像狗一樣憑嗅覺找到主人的身邊。作為豬中“貴族”，麝香豬非常喜愛乾淨，出生後就知道如廁有專門地點，決不會吃喝拉撒均在一處。但要經常給他洗澡,否則就會變成一隻'臭豬'的，而香豬不會散發出香味的。");
		Animal a7 = new Animal("柯利黛綿羊",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/e3-82-b3-e3-83-aa-e3-83-86-e3-82-99-e2-80-95-e3-83-ab_-5-285x176.jpg",
				"Corriedale是新西蘭的綿羊品種。它是由詹姆斯·利特（James Little）於1882年左右在南島繁殖的，他將美麗諾羊和林肯長毛綿羊雜交育成。該品種在1911年被正式認可。它已出口到澳大利亞以及非洲，亞洲，歐洲以及北美洲和南美洲的許多國家。據報導，2021年有25個國家/地區的人口總數剛好超過500萬。");
		z2.addAnimal(Arrays.asList(a5, a6, a7));

		Animal a8 = new Animal("羊駝",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/16-02-05_0177-2-285x176-1.jpg",
				"羊駝被人工馴養超過600年，其歷史可以追溯到印加帝國早期。在歐洲人侵略美洲大陸之前，羊駝和牠們的表親大羊駝在南美洲是僅有的被馴化的牲畜，是安地斯文明整體的一部分也是其文明生活方式的體現。羊駝可用於提供食物、燃料、衣物和運輸畜力。在西班牙占領印加後，許多羊駝、大羊駝和原住民一樣消亡了，但在居住條件不合適人類的阿爾蒂普拉諾高原（Altiplano），這個物種倖存了下來。如今羊駝數量超過了三百萬，但在南美洲以外，生存量則非常小（約4萬）。羊駝是高度群居的動物，容易飼養和照看。羊駝的外表看起來很有趣，使得許多觀光牧場飼養牠們。牠們主要通過身體姿勢和柔和的哼唱聲進行交流。一般來說大羊駝的重量範圍大約在100-200kg，而羊駝大約是70-90kg。");
		z3.addAnimal(Arrays.asList(a8));

		Animal a9 = new Animal("雙峰駱駝",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2017/01/1DX_0473_R-285x176.jpg",
				"雙峰駱駝（學名：Camelus bactrianus），也叫雙峰駝，是一個大型的偶蹄動物，與單峰駱駝不同，有雙峰，主要棲息在中亞與土耳其。雙峰駝因其耐寒，耐旱和對高海拔地區的適應力而於中亞長期馴養作馱畜，例如絲綢之路的駱駝商隊。今天全世界大約有二百萬馴養的雙峰駱駝。有少數的野生雙峰駝出沒於哈薩克斯坦西南部的曼格斯套州以及巴基斯坦和印度的克什米爾山谷，並會在冬季從沙漠遷移到西伯利亞的河流，澳洲亦有野化的雙峰駝。近年發現，分佈在中國和蒙古的野外偏遠地區，包括戈壁和塔克拉瑪干沙漠的野生雙峰駝（Camelus ferus）是另一個獨立物種，這兩種雙峰駝的最後共同祖先大約在110萬年前就已經分開演化，2002年10月數據顯示大約還有800頭野生雙峰駝，世界自然保護聯盟（IUCN）已將該物種列為極度瀕危。");
		Animal a10 = new Animal("馬",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/09/106988a0de060c97c7a4ab1215e17aa3-285x176.jpg",
				"馬（學名：Equus ferus caballus）是一種草食性家畜，是野馬的亞種，廣泛分布於世界各地。目前全球約有5,800萬匹馬，共計905個品種。現代家馬原產於歐亞大陸中心，源於6000多年前就被人類馴養的歐洲野馬。早期的馬匹馴養遺址於烏克蘭草原、哈薩克中亞草原等地被發現。美洲大陸原生的野馬族群約於公元前兩萬年滅絕，直至15世紀後，歐洲殖民者才將歐亞大陸的家馬引進美洲和未曾產馬的澳洲。馬的成功馴化影響了人類的歷史進程。牠們幫助人類建設城鎮、耕種土地、開拓疆域，曾是人類的主要運輸方式之一。在人類歷史上象徵了勝利、主權、財富、貴族和死亡等。現代馬雖然已經對人類的影響不再那麼重要，但仍然是解剖學、系譜學和運動科學中的關鍵模型生物。");
		Animal a11 = new Animal("小馬",
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/e3-83-9b-e3-82-9a-e3-83-8b-e3-83-bc_-3-285x176.jpg",
				"馬（學名：Equus ferus caballus）是一種草食性家畜，是野馬的亞種，廣泛分布於世界各地。目前全球約有5,800萬匹馬，共計905個品種。現代家馬原產於歐亞大陸中心，源於6000多年前就被人類馴養的歐洲野馬。早期的馬匹馴養遺址於烏克蘭草原、哈薩克中亞草原等地被發現。美洲大陸原生的野馬族群約於公元前兩萬年滅絕，直至15世紀後，歐洲殖民者才將歐亞大陸的家馬引進美洲和未曾產馬的澳洲。馬的成功馴化影響了人類的歷史進程。牠們幫助人類建設城鎮、耕種土地、開拓疆域，曾是人類的主要運輸方式之一。在人類歷史上象徵了勝利、主權、財富、貴族和死亡等。現代馬雖然已經對人類的影響不再那麼重要，但仍然是解剖學、系譜學和運動科學中的關鍵模型生物。");
		z4.addAnimal(Arrays.asList(a9, a10, a11));

		Animal a12 = new Animal("亞洲小爪水獺", "ari75-wh3e1.jpg",
				"亞洲小爪水獺（學名Aonyx cinerea、oriental small-clawed otter、 Asian small-clawed otter），又名小爪水獺，是一種小型水獺。主要分佈於孟加拉國、印度、中國、中南半島、馬來半島、印度尼西亞和菲律賓等地區的濕地附近。亞洲小爪水獺一般體長0.9米（包括尾巴），體重可達5公斤。以魚、蛙、蟹、螯蝦和貝類為食。");
		z5.addAnimal(Arrays.asList(a12));

		Animal a13 = new Animal("日本松鼠", "squirrel-animal-cute-rodents-47547.jpg",
				"日本松鼠（拉丁文學名：Sciurus lis）是囓齒目松鼠科松鼠屬下的一個品種，為日本特有種。只在本州及四國上出現，但在中國地方及九州則已罕見。松鼠一般以植食性為主，食物主要是種子和果仁，也會吃鳥蛋，水果如櫻桃、草莓等。部分物種會食昆蟲，其中一些熱帶物種更會為捕食昆蟲而進行遷徙，甚至叼走山雀雛鳥。松鼠多在春、夏季發情，發情期大約為兩個星期左右。松鼠繁殖的適齡期，雌性為8－9周齡，雄鼠為9－10周齡。在非繁殖季節，雌雄性成體分別佔據一定的地方作為自己生活的活動範圍。在野外，雌性保護一定面積的地盤是為了保護食物，其內不允許其他同種個體進入，對雄性個體也是如此。但是在繁殖季節，則明顯地放鬆對領域範圍的保護，允許雄性個體進入。");
		z6.addAnimal(Arrays.asList(a13));
		// ------------------------------------------------------------------------

		TicketInfo t0 = new TicketInfo("成人票", "13歲以上請購買成人票", null, "", 2200, TicketType.ENTRANCE,
				"zoo_pass_qrcode.png", "成人票");
		TicketInfo t1 = new TicketInfo("兒童票", "6歲至12歲請購買兒童票", null, "", 1200, TicketType.ENTRANCE,
				"zoo_pass_qrcode.png", "兒童票");
		TicketInfo t2 = new TicketInfo("幼兒票", "5歲以下請購買幼兒票", null, "", 500, TicketType.ENTRANCE, "zoo_pass_qrcode.png",
				"幼兒票");
		TicketInfo t3 = new TicketInfo("老人票", "65歲以上請購買敬老票", null, "", 1600, TicketType.ENTRANCE,
				"zoo_pass_qrcode.png", "老人票");
		TicketInfo t4 = new TicketInfo("近距離接觸陸龜", "除了可享受餵食蔬果給陸龜的體驗之外，還可以近距離接觸陸龜。\n※將根據天氣狀況，可能在室內、戶外展示。", z1,
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/1bfc8aa00e515b25d25d48e3016e2e9e.jpg",
				0,
				TicketType.EXPERIENCE, "turtle_touch_qrcode.png", "體驗票");
		TicketInfo t5 = new TicketInfo("近距離接觸羊兒", "可愛的羊兒們居住的區域，可享受飼料餵食體驗。", z2,
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/a136f339af8c799ea9f58e6818f14f6e.jpg",
				0,
				TicketType.EXPERIENCE, "dark_sheep_feeding_qrcode.png", "體驗票");
		TicketInfo t6 = new TicketInfo("羊駝飼料餵食體驗", "可以近距離親近戶外廣場的羊駝。\n推薦輕撫牠們柔軟的毛、餵食飼料，都是相當不錯的經驗喔!!", z3,
				"https://tw.kobe-oukoku.com/wp-content/uploads/2016/03/0e5a2b0a0b2630ff99b6b9720e93e7e6.jpg",
				0, TicketType.EXPERIENCE, "alpaca_feed_qrcode.png", "體驗票");
		zoo.addTicketInfos((Arrays.asList(t0, t1, t2, t3, t4, t5, t6)));

		zoo.addCustomer(Arrays.asList(new Customer("admin")));
		try {
			zoo.buyTicket("admin", "2000-01-05", "幼兒票");
			System.out.print(1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		router.register("get/tickets/", s -> {
			try {
				if (s != null) {
					if (s.get("kind").toString().equals("user")) {
						var tickets = zoo.getCustomer(s.get("id").toString()).getTickets();
						String[] animal_pics = zoo.getAnimals().stream().map(Animal::getPath).toArray(String[]::new);
						for (var x : tickets) {
							if (x.tinfo.path.isEmpty()) {
								x.tinfo.path = animal_pics[(int) (Math.random() * animal_pics.length)];
							}
						}
						return tickets;
					} else if (s.get("kind").toString().equals("entrance")) {
						return zoo.getTicketInfos().stream()
								.filter(x -> x.kind == TicketType.ENTRANCE).toList();
					} else if (s.get("kind").toString().equals("experience")) {
						if (s.containsKey("name")) {
							return zoo.getTicketInfos().stream()
									.filter(x -> x.kind == TicketType.EXPERIENCE
											&& x.name.equals(s.get("name").toString()))
									.findAny().orElseThrow();
						} else {
							return zoo.getTicketInfos().stream()
									.filter(x -> x.kind == TicketType.EXPERIENCE).toList();
						}
					}
				}
			} catch (Exception e) {
			}
			return null;
		});
		router.register("post/tickets/", s -> {
			try {
				if (s != null) {
					if (s.containsKey("id") && s.containsKey("tname") && s.containsKey("date")) {
						zoo.buyTicket(s.get("id").toString(), s.get("date").toString(),
								s.get("tname").toString());
						return "";
					}

				}
			} catch (Exception e) {
			}
			return null;
		});
		router.register("get/tickets_length/", s -> {
			try {
				if (s != null) {
					if (s.containsKey("id")) {
						return zoo.getCustomer(s.get("id").toString()).getTickets().size();
					}
				}
			} catch (Exception e) {
			}
			return null;
		});
		// router.register("delete/tickets/", s -> {
		// try {
		// if (s != null) {
		// if (s.containsKey("id")) {
		// zoo.cancelTicket(s.get("id").toString());
		// return "";
		// }

		// }
		// } catch (Exception e) {
		// }
		// return null;
		// });
		router.register("get/animals/", s -> {
			try {
				if (s != null) {
					if (s.containsKey("start") && s.containsKey("end")) {
						return zoo.getAnimals().subList((int) Double.parseDouble(s.get(
								"start").toString()),
								(int) Double.parseDouble(s.get("end").toString())).stream().map(Animal::getName)
								.collect(Collectors.toList());
					} else if (s.containsKey("name")) {
						return zoo.getAnimals().stream().filter(x -> x.name.equals(s.get("name").toString()))
								.findFirst().orElse(null);
					}
				} else {
					List<String> names = zoo.getAnimals().stream().map(Animal::getName).collect(Collectors.toList());
					return names;
				}
			} catch (Exception e) {
			}
			return null;
		});
		router.register("get/zones/", s -> {
			try {
				if (s != null) {
					return zoo.getZones().stream().filter(x -> x.name.equals(s.get("name").toString())).findAny()
							.orElseThrow();
				} else {
					return zoo.getZones().stream().map(Zone::getName).collect(Collectors.toList());
				}
			} catch (Exception e) {
			}
			return null;
		});
		router.register("get/linemap/", s -> {
			try {
				if (s != null) {
					if (s.containsKey("start") && s.containsKey("end")) {
						return zoo.getLinemap(s.get("start").toString(), s.get("end").toString());
					}

				}
			} catch (Exception e) {
			}
			return null;
		});
	}
}
