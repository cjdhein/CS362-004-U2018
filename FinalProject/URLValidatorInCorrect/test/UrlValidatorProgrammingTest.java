/*
 * TODO:
 * 
 * 1. enable all value combos to UrlValidator
 * 2. ports
 * 3. queries
 * 4. fragments
 * 
 * 
 */



import java.util.Random;

import junit.framework.TestCase;

public class UrlValidatorTest2 extends TestCase {
	
	// use these booleans to turn off generation of individual test data
	// (used for testing test function)
	public boolean testSchemes = true;
	public boolean testAuthorities = true;
	public boolean testPaths = true;
	public boolean testQueries = true; //true;
	public boolean testFragments = true;//true;
	
	String schemesToUse[] = {
			"file",
			"ftp",
			"http",
			"https"
	};
	
	// valid schemes (currently only 3 are being used, the rest are not implemented)
	String validSchemes[] = {
			/*	"aaa",
				"aaas",
				"about",
				"acap",
				"acct",
				"acr",
				"adiumxtra",
				"afp",
				"afs",
				"aim",
				"appdata",
				"apt",
				"attachment",
				"aw",
				"barion",
				"beshare",
				"bitcoin",
				"blob",
				"bolo",
				"browserext",
				"callto",
				"cap",
				"chrome",
				"chrome-extension",
				"cid",
				"coap",
				"coap+tcp",
				"coap+ws",
				"coaps",
				"coaps+tcp",
				"coaps+ws",
				"com-eventbrite-attendee",
				"content",
				"conti",
				"crid",
				"cvs",
				"data",
				"dav",
				"diaspora",
				"dict",
				"did",
				"dis",
				"dlna-playcontainer",
				"dlna-playsingle",
				"dns",
				"dntp",
				"dtn",
				"dvb",
				"ed2k",
				"elsi",
				"example",
				"facetime",
				"fax",
				"feed",
				"feedready",
				"file", 
				"filesystem",
				"finger",
				"fish",
		*/		"ftp",
			/*	"geo",
				"gg",
				"git",
				"gizmoproject",
				"go",
				"gopher",
				"graph",
				"gtalk",
				"h323",
				"ham",
				"hcap",
				"hcp",
			*/	"http",
				"https",
			/*	"hxxp",
				"hxxps",
				"hydrazone",
				"iax",
				"icap",
				"icon",
				"im",
				"imap",
				"info",
				"iotdisco",
				"ipn",
				"ipp",
				"ipps",
				"irc",
				"irc6",
				"ircs",
				"iris",
				"iris.beep",
				"iris.lwz",
				"iris.xpc",
				"iris.xpcs",
				"isostore",
				"itms",
				"jabber",
				"jar",
				"jms",
				"keyparc",
				"lastfm",
				"ldap",
				"ldaps",
				"lvlt",
				"magnet",
				"mailserver",
				"mailto",
				"maps",
				"market",
				"message",
				"microsoft.windows.camera",
				"microsoft.windows.camera.multipicker",
				"microsoft.windows.camera.picker",
				"mid",
				"mms",
				"modem",
				"mongodb",
				"moz",
				"ms-access",
				"ms-browser-extension",
				"ms-drive-to",
				"ms-enrollment",
				"ms-excel",
				"ms-gamebarservices",
				"ms-gamingoverlay",
				"ms-getoffice",
				"ms-help",
				"ms-infopath",
				"ms-inputapp",
				"ms-lockscreencomponent-config",
				"ms-media-stream-id",
				"ms-mixedrealitycapture",
				"ms-officeapp",
				"ms-people",
				"ms-project",
				"ms-powerpoint",
				"ms-publisher",
				"ms-restoretabcompanion",
				"ms-screenclip",
				"ms-screensketch",
				"ms-search-repair",
				"ms-secondary-screen-controller",
				"ms-secondary-screen-setup",
				"ms-settings",
				"ms-settings-airplanemode",
				"ms-settings-bluetooth",
				"ms-settings-camera",
				"ms-settings-cellular",
				"ms-settings-cloudstorage",
				"ms-settings-connectabledevices",
				"ms-settings-displays-topology",
				"ms-settings-emailandaccounts",
				"ms-settings-language",
				"ms-settings-location",
				"ms-settings-lock",
				"ms-settings-nfctransactions",
				"ms-settings-notifications",
				"ms-settings-power",
				"ms-settings-privacy",
				"ms-settings-proximity",
				"ms-settings-screenrotation",
				"ms-settings-wifi",
				"ms-settings-workplace",
				"ms-spd",
				"ms-sttoverlay",
				"ms-transit-to",
				"ms-useractivityset",
				"ms-virtualtouchpad",
				"ms-visio",
				"ms-walk-to",
				"ms-whiteboard",
				"ms-whiteboard-cmd",
				"ms-word",
				"msnim",
				"msrp",
				"msrps",
				"mtqp",
				"mumble",
				"mupdate",
				"mvn",
				"news",
				"nfs",
				"ni",
				"nih",
				"nntp",
				"notes",
				"ocf",
				"oid",
				"onenote",
				"onenote-cmd",
				"opaquelocktoken",
				"openpgp4fpr",
				"pack",
				"palm",
				"paparazzi",
				"pkcs11",
				"platform",
				"pop",
				"pres",
				"prospero",
				"proxy",
				"pwid",
				"psyc",
				"qb",
				"query",
				"redis",
				"rediss",
				"reload",
				"res",
				"resource",
				"rmi",
				"rsync",
				"rtmfp",
				"rtmp",
				"rtsp",
				"rtsps",
				"rtspu",
				"secondlife",
				"service",
				"session",
				"sftp",
				"sgn",
				"shttp",
				"sieve",
				"simpleledger",
				"sip",
				"sips",
				"skype",
				"smb",
				"sms",
				"smtp",
				"snews",
				"snmp",
				"soap.beep",
				"soap.beeps",
				"soldat",
				"spiffe",
				"spotify",
				"ssh",
				"steam",
				"stun",
				"stuns",
				"submit",
				"svn",
				"tag",
				"teamspeak",
				"tel",
				"teliaeid",
				"telnet",
				"tftp",
				"things",
				"thismessage",
				"tip",
				"tn3270",
				"tool",
				"turn",
				"turns",
				"tv",
				"udp",
				"unreal",
				"urn",
				"ut2004",
				"v-event",
				"vemmi",
				"ventrilo",
				"videotex",
				"vnc",
				"view-source",
				"wais",
				"webcal",
				"wpid",
				"ws",
				"wss",
				"wtai",
				"wyciwyg",
				"xcon",
				"xcon-userid",
				"xfire",
				"xmlrpc.beep",
				"xmlrpc.beeps",
				"xmpp",
				"xri",
				"ymsgr",
				"z39.50",
				"z39.50r",
				"z39.50s" */
		};
	
	String validTLDs[] = { "AAA",
			"AARP",
			"ABARTH",
			"ABB",
			"ABBOTT",
			"ABBVIE",
			"ABC",
			"ABLE",
			"ABOGADO",
			"ABUDHABI",
			"AC",
			"ACADEMY",
			"ACCENTURE",
			"ACCOUNTANT",
			"ACCOUNTANTS",
			"ACO",
			"ACTIVE",
			"ACTOR",
			"AD",
			"ADAC",
			"ADS",
			"ADULT",
			"AE",
			"AEG",
			"AERO",
			"AETNA",
			"AF",
			"AFAMILYCOMPANY",
			"AFL",
			//"AFRICA",
			"AG",
			"AGAKHAN",
			"AGENCY",
			"AI",
			"AIG",
			"AIGO",
			"AIRBUS",
			"AIRFORCE",
			"AIRTEL",
			"AKDN",
			"AL",
			"ALFAROMEO",
			"ALIBABA",
			"ALIPAY",
			"ALLFINANZ",
			"ALLSTATE",
			"ALLY",
			"ALSACE",
			"ALSTOM",
			"AM",
			"AMERICANEXPRESS",
			"AMERICANFAMILY",
			"AMEX",
			"AMFAM",
			"AMICA",
			"AMSTERDAM",
			"ANALYTICS",
			"ANDROID",
			"ANQUAN",
			"ANZ",
			"AO",
			"AOL",
			"APARTMENTS",
			"APP",
			"APPLE",
			"AQ",
			"AQUARELLE",
			"AR",
			//"ARAB",
			"ARAMCO",
			"ARCHI",
			"ARMY",
			"ARPA",
			"ART",
			"ARTE",
			"AS",
			"ASDA",
			"ASIA",
			"ASSOCIATES",
			"AT",
			"ATHLETA",
			"ATTORNEY",
			"AU",
			"AUCTION",
			"AUDI",
			"AUDIBLE",
			"AUDIO",
			"AUSPOST",
			"AUTHOR",
			"AUTO",
			"AUTOS",
			"AVIANCA",
			"AW",
			"AWS",
			"AX",
			"AXA",
			"AZ",
			"AZURE",
			"BA",
			"BABY",
			"BAIDU",
			"BANAMEX",
			"BANANAREPUBLIC",
			"BAND",
			"BANK",
			"BAR",
			"BARCELONA",
			"BARCLAYCARD",
			"BARCLAYS",
			"BAREFOOT",
			"BARGAINS",
			"BASEBALL",
			"BASKETBALL",
			"BAUHAUS",
			"BAYERN",
			"BB",
			"BBC",
			"BBT",
			"BBVA",
			"BCG",
			"BCN",
			"BD",
			"BE",
			"BEATS",
			"BEAUTY",
			"BEER",
			"BENTLEY",
			"BERLIN",
			"BEST",
			"BESTBUY",
			"BET",
			"BF",
			"BG",
			"BH",
			"BHARTI",
			"BI",
			"BIBLE",
			"BID",
			"BIKE",
			"BING",
			"BINGO",
			"BIO",
			"BIZ",
			"BJ",
			"BLACK",
			"BLACKFRIDAY",
			"BLANCO",
			"BLOCKBUSTER",
			"BLOG",
			"BLOOMBERG",
			"BLUE",
			"BM",
			"BMS",
			"BMW",
			"BN",
			"BNL",
			"BNPPARIBAS",
			"BO",
			"BOATS",
			"BOEHRINGER",
			"BOFA",
			"BOM",
			"BOND",
			"BOO",
			"BOOK",
			"BOOKING",
			"BOSCH",
			"BOSTIK",
			"BOSTON",
			"BOT",
			"BOUTIQUE",
			"BOX",
			"BR",
			"BRADESCO",
			"BRIDGESTONE",
			"BROADWAY",
			"BROKER",
			"BROTHER",
			"BRUSSELS",
			"BS",
			"BT",
			"BUDAPEST",
			"BUGATTI",
			"BUILD",
			"BUILDERS",
			"BUSINESS",
			"BUY",
			"BUZZ",
			"BV",
			"BW",
			"BY",
			"BZ",
			"BZH",
			"CA",
			"CAB",
			"CAFE",
			"CAL",
			"CALL",
			"CALVINKLEIN",
			"CAM",
			"CAMERA",
			"CAMP",
			"CANCERRESEARCH",
			"CANON",
			"CAPETOWN",
			"CAPITAL",
			"CAPITALONE",
			"CAR",
			"CARAVAN",
			"CARDS",
			"CARE",
			"CAREER",
			"CAREERS",
			"CARS",
			"CARTIER",
			"CASA",
			"CASE",
			"CASEIH",
			"CASH",
			"CASINO",
			"CAT",
			"CATERING",
			"CATHOLIC",
			"CBA",
			"CBN",
			"CBRE",
			"CBS",
			"CC",
			"CD",
			"CEB",
			"CENTER",
			"CEO",
			"CERN",
			"CF",
			"CFA",
			"CFD",
			"CG",
			"CH",
			"CHANEL",
			"CHANNEL",
			//"CHARITY",
			"CHASE",
			"CHAT",
			"CHEAP",
			"CHINTAI",
			"CHRISTMAS",
			"CHROME",
			"CHRYSLER",
			"CHURCH",
			"CI",
			"CIPRIANI",
			"CIRCLE",
			"CISCO",
			"CITADEL",
			"CITI",
			"CITIC",
			"CITY",
			"CITYEATS",
			"CK",
			"CL",
			"CLAIMS",
			"CLEANING",
			"CLICK",
			"CLINIC",
			"CLINIQUE",
			"CLOTHING",
			"CLOUD",
			"CLUB",
			"CLUBMED",
			"CM",
			"CN",
			"CO",
			"COACH",
			"CODES",
			"COFFEE",
			"COLLEGE",
			"COLOGNE",
			"COM",
			"COMCAST",
			"COMMBANK",
			"COMMUNITY",
			"COMPANY",
			"COMPARE",
			"COMPUTER",
			"COMSEC",
			"CONDOS",
			"CONSTRUCTION",
			"CONSULTING",
			"CONTACT",
			"CONTRACTORS",
			"COOKING",
			"COOKINGCHANNEL",
			"COOL",
			"COOP",
			"CORSICA",
			"COUNTRY",
			"COUPON",
			"COUPONS",
			"COURSES",
			"CR",
			"CREDIT",
			"CREDITCARD",
			"CREDITUNION",
			"CRICKET",
			"CROWN",
			"CRS",
			"CRUISE",
			"CRUISES",
			"CSC",
			"CU",
			"CUISINELLA",
			"CV",
			"CW",
			"CX",
			"CY",
			"CYMRU",
			"CYOU",
			"CZ",
			"DABUR",
			"DAD",
			"DANCE",
			"DATA",
			"DATE",
			"DATING",
			"DATSUN",
			"DAY",
			"DCLK",
			"DDS",
			"DE",
			"DEAL",
			"DEALER",
			"DEALS",
			"DEGREE",
			"DELIVERY",
			"DELL",
			"DELOITTE",
			"DELTA",
			"DEMOCRAT",
			"DENTAL",
			"DENTIST",
			"DESI",
			"DESIGN",
			"DEV",
			"DHL",
			"DIAMONDS",
			"DIET",
			"DIGITAL",
			"DIRECT",
			"DIRECTORY",
			"DISCOUNT",
			"DISCOVER",
			"DISH",
			"DIY",
			"DJ",
			"DK",
			"DM",
			"DNP",
			"DO",
			"DOCS",
			"DOCTOR",
			"DODGE",
			"DOG",
			"DOHA",
			"DOMAINS",
			"DOT",
			"DOWNLOAD",
			"DRIVE",
			"DTV",
			"DUBAI",
			"DUCK",
			"DUNLOP",
			"DUNS",
			"DUPONT",
			"DURBAN",
			"DVAG",
			"DVR",
			"DZ",
			"EARTH",
			"EAT",
			"EC",
			"ECO",
			"EDEKA",
			"EDU",
			"EDUCATION",
			"EE",
			"EG",
			"EMAIL",
			"EMERCK",
			"ENERGY",
			"ENGINEER",
			"ENGINEERING",
			"ENTERPRISES",
			"EPOST",
			"EPSON",
			"EQUIPMENT",
			"ER",
			"ERICSSON",
			"ERNI",
			"ES",
			"ESQ",
			"ESTATE",
			"ESURANCE",
			"ET",
			//"ETISALAT",
			"EU",
			"EUROVISION",
			"EUS",
			"EVENTS",
			"EVERBANK",
			"EXCHANGE",
			"EXPERT",
			"EXPOSED",
			"EXPRESS",
			"EXTRASPACE",
			"FAGE",
			"FAIL",
			"FAIRWINDS",
			"FAITH",
			"FAMILY",
			"FAN",
			"FANS",
			"FARM",
			"FARMERS",
			"FASHION",
			"FAST",
			"FEDEX",
			"FEEDBACK",
			"FERRARI",
			"FERRERO",
			"FI",
			"FIAT",
			"FIDELITY",
			"FIDO",
			"FILM",
			"FINAL",
			"FINANCE",
			"FINANCIAL",
			"FIRE",
			"FIRESTONE",
			"FIRMDALE",
			"FISH",
			"FISHING",
			"FIT",
			"FITNESS",
			"FJ",
			"FK",
			"FLICKR",
			"FLIGHTS",
			"FLIR",
			"FLORIST",
			"FLOWERS",
			"FLY",
			"FM",
			"FO",
			"FOO",
			"FOOD",
			"FOODNETWORK",
			"FOOTBALL",
			"FORD",
			"FOREX",
			"FORSALE",
			"FORUM",
			"FOUNDATION",
			"FOX",
			"FR",
			"FREE",
			"FRESENIUS",
			"FRL",
			"FROGANS",
			"FRONTDOOR",
			"FRONTIER",
			"FTR",
			"FUJITSU",
			"FUJIXEROX",
			"FUN",
			"FUND",
			"FURNITURE",
			"FUTBOL",
			"FYI",
			"GA",
			"GAL",
			"GALLERY",
			"GALLO",
			"GALLUP",
			"GAME",
			"GAMES",
			"GAP",
			"GARDEN",
			"GB",
			"GBIZ",
			"GD",
			"GDN",
			"GE",
			"GEA",
			"GENT",
			"GENTING",
			"GEORGE",
			"GF",
			"GG",
			"GGEE",
			"GH",
			"GI",
			"GIFT",
			"GIFTS",
			"GIVES",
			"GIVING",
			"GL",
			"GLADE",
			"GLASS",
			"GLE",
			"GLOBAL",
			"GLOBO",
			"GM",
			"GMAIL",
			"GMBH",
			"GMO",
			"GMX",
			"GN",
			"GODADDY",
			"GOLD",
			"GOLDPOINT",
			"GOLF",
			"GOO",
			"GOODHANDS",
			"GOODYEAR",
			"GOOG",
			"GOOGLE",
			"GOP",
			"GOT",
			"GOV",
			"GP",
			"GQ",
			"GR",
			"GRAINGER",
			"GRAPHICS",
			"GRATIS",
			"GREEN",
			"GRIPE",
			//"GROCERY",
			"GROUP",
			"GS",
			"GT",
			"GU",
			"GUARDIAN",
			"GUCCI",
			"GUGE",
			"GUIDE",
			"GUITARS",
			"GURU",
			"GW",
			"GY",
			"HAIR",
			"HAMBURG",
			"HANGOUT",
			"HAUS",
			"HBO",
			"HDFC",
			"HDFCBANK",
			"HEALTH",
			"HEALTHCARE",
			"HELP",
			"HELSINKI",
			"HERE",
			"HERMES",
			"HGTV",
			"HIPHOP",
			"HISAMITSU",
			"HITACHI",
			"HIV",
			"HK",
			"HKT",
			"HM",
			"HN",
			"HOCKEY",
			"HOLDINGS",
			"HOLIDAY",
			"HOMEDEPOT",
			"HOMEGOODS",
			"HOMES",
			"HOMESENSE",
			"HONDA",
			"HONEYWELL",
			"HORSE",
			"HOSPITAL",
			"HOST",
			"HOSTING",
			"HOT",
			"HOTELES",
			//"HOTELS",
			"HOTMAIL",
			"HOUSE",
			"HOW",
			"HR",
			"HSBC",
			"HT",
			"HU",
			"HUGHES",
			"HYATT",
			"HYUNDAI",
			"IBM",
			"ICBC",
			"ICE",
			"ICU",
			"ID",
			"IE",
			"IEEE",
			"IFM",
			"IKANO",
			"IL",
			"IM",
			"IMAMAT",
			"IMDB",
			"IMMO",
			"IMMOBILIEN",
			"IN",
			//"INC",
			"INDUSTRIES",
			"INFINITI",
			"INFO",
			"ING",
			"INK",
			"INSTITUTE",
			"INSURANCE",
			"INSURE",
			"INT",
			"INTEL",
			"INTERNATIONAL",
			"INTUIT",
			"INVESTMENTS",
			"IO",
			"IPIRANGA",
			"IQ",
			"IR",
			"IRISH",
			"IS",
			"ISELECT",
			"ISMAILI",
			"IST",
			"ISTANBUL",
			"IT",
			"ITAU",
			"ITV",
			"IVECO",
			"JAGUAR",
			"JAVA",
			"JCB",
			"JCP",
			"JE",
			"JEEP",
			"JETZT",
			"JEWELRY",
			"JIO",
			"JLC",
			"JLL",
			"JM",
			"JMP",
			"JNJ",
			"JO",
			"JOBS",
			"JOBURG",
			"JOT",
			"JOY",
			"JP",
			"JPMORGAN",
			"JPRS",
			"JUEGOS",
			"JUNIPER",
			"KAUFEN",
			"KDDI",
			"KE",
			"KERRYHOTELS",
			"KERRYLOGISTICS",
			"KERRYPROPERTIES",
			"KFH",
			"KG",
			"KH",
			"KI",
			"KIA",
			"KIM",
			"KINDER",
			"KINDLE",
			"KITCHEN",
			"KIWI",
			"KM",
			"KN",
			"KOELN",
			"KOMATSU",
			"KOSHER",
			"KP",
			"KPMG",
			"KPN",
			"KR",
			"KRD",
			"KRED",
			"KUOKGROUP",
			"KW",
			"KY",
			"KYOTO",
			"KZ",
			"LA",
			"LACAIXA",
			"LADBROKES",
			"LAMBORGHINI",
			"LAMER",
			"LANCASTER",
			"LANCIA",
			"LANCOME",
			"LAND",
			"LANDROVER",
			"LANXESS",
			"LASALLE",
			"LAT",
			"LATINO",
			"LATROBE",
			"LAW",
			"LAWYER",
			"LB",
			"LC",
			"LDS",
			"LEASE",
			"LECLERC",
			"LEFRAK",
			"LEGAL",
			"LEGO",
			"LEXUS",
			"LGBT",
			"LI",
			"LIAISON",
			"LIDL",
			"LIFE",
			"LIFEINSURANCE",
			"LIFESTYLE",
			"LIGHTING",
			"LIKE",
			"LILLY",
			"LIMITED",
			"LIMO",
			"LINCOLN",
			"LINDE",
			"LINK",
			"LIPSY",
			"LIVE",
			"LIVING",
			"LIXIL",
			"LK",
			//"LLC",
			"LOAN",
			"LOANS",
			"LOCKER",
			"LOCUS",
			"LOFT",
			"LOL",
			"LONDON",
			"LOTTE",
			"LOTTO",
			"LOVE",
			"LPL",
			"LPLFINANCIAL",
			"LR",
			"LS",
			"LT",
			"LTD",
			"LTDA",
			"LU",
			"LUNDBECK",
			"LUPIN",
			"LUXE",
			"LUXURY",
			"LV",
			"LY",
			"MA",
			"MACYS",
			"MADRID",
			"MAIF",
			"MAISON",
			"MAKEUP",
			"MAN",
			"MANAGEMENT",
			"MANGO",
			//"MAP",
			"MARKET",
			"MARKETING",
			"MARKETS",
			"MARRIOTT",
			"MARSHALLS",
			"MASERATI",
			"MATTEL",
			"MBA",
			"MC",
			"MCKINSEY",
			"MD",
			"ME",
			"MED",
			"MEDIA",
			"MEET",
			"MELBOURNE",
			"MEME",
			"MEMORIAL",
			"MEN",
			"MENU",
			//"MERCKMSD",
			"METLIFE",
			"MG",
			"MH",
			"MIAMI",
			"MICROSOFT",
			"MIL",
			"MINI",
			"MINT",
			"MIT",
			"MITSUBISHI",
			"MK",
			"ML",
			"MLB",
			"MLS",
			"MM",
			"MMA",
			"MN",
			"MO",
			"MOBI",
			"MOBILE",
			"MOBILY",
			"MODA",
			"MOE",
			"MOI",
			"MOM",
			"MONASH",
			"MONEY",
			"MONSTER",
			"MOPAR",
			"MORMON",
			"MORTGAGE",
			"MOSCOW",
			"MOTO",
			"MOTORCYCLES",
			"MOV",
			"MOVIE",
			"MOVISTAR",
			"MP",
			"MQ",
			"MR",
			"MS",
			"MSD",
			"MT",
			"MTN",
			"MTR",
			"MU",
			"MUSEUM",
			"MUTUAL",
			"MV",
			"MW",
			"MX",
			"MY",
			"MZ",
			"NA",
			"NAB",
			"NADEX",
			"NAGOYA",
			"NAME",
			"NATIONWIDE",
			"NATURA",
			"NAVY",
			"NBA",
			"NC",
			"NE",
			"NEC",
			"NET",
			"NETBANK",
			"NETFLIX",
			"NETWORK",
			"NEUSTAR",
			"NEW",
			"NEWHOLLAND",
			"NEWS",
			"NEXT",
			"NEXTDIRECT",
			"NEXUS",
			"NF",
			"NFL",
			"NG",
			"NGO",
			"NHK",
			"NI",
			"NICO",
			"NIKE",
			"NIKON",
			"NINJA",
			"NISSAN",
			"NISSAY",
			"NL",
			"NO",
			"NOKIA",
			"NORTHWESTERNMUTUAL",
			"NORTON",
			"NOW",
			"NOWRUZ",
			"NOWTV",
			"NP",
			"NR",
			"NRA",
			"NRW",
			"NTT",
			"NU",
			"NYC",
			"NZ",
			"OBI",
			"OBSERVER",
			"OFF",
			"OFFICE",
			"OKINAWA",
			"OLAYAN",
			"OLAYANGROUP",
			"OLDNAVY",
			"OLLO",
			"OM",
			"OMEGA",
			"ONE",
			"ONG",
			"ONL",
			"ONLINE",
			"ONYOURSIDE",
			"OOO",
			"OPEN",
			"ORACLE",
			"ORANGE",
			"ORG",
			"ORGANIC",
			"ORIGINS",
			"OSAKA",
			"OTSUKA",
			"OTT",
			"OVH",
			"PA",
			"PAGE",
			"PANASONIC",
			"PANERAI",
			"PARIS",
			"PARS",
			"PARTNERS",
			"PARTS",
			"PARTY",
			"PASSAGENS",
			"PAY",
			"PCCW",
			"PE",
			"PET",
			"PF",
			"PFIZER",
			"PG",
			"PH",
			"PHARMACY",
			//"PHD",
			"PHILIPS",
			"PHONE",
			"PHOTO",
			"PHOTOGRAPHY",
			"PHOTOS",
			"PHYSIO",
			"PIAGET",
			"PICS",
			"PICTET",
			"PICTURES",
			"PID",
			"PIN",
			"PING",
			"PINK",
			"PIONEER",
			"PIZZA",
			"PK",
			"PL",
			"PLACE",
			"PLAY",
			"PLAYSTATION",
			"PLUMBING",
			"PLUS",
			"PM",
			"PN",
			"PNC",
			"POHL",
			"POKER",
			"POLITIE",
			"PORN",
			"POST",
			"PR",
			"PRAMERICA",
			"PRAXI",
			"PRESS",
			"PRIME",
			"PRO",
			"PROD",
			"PRODUCTIONS",
			"PROF",
			"PROGRESSIVE",
			"PROMO",
			"PROPERTIES",
			"PROPERTY",
			"PROTECTION",
			"PRU",
			"PRUDENTIAL",
			"PS",
			"PT",
			"PUB",
			"PW",
			"PWC",
			"PY",
			"QA",
			"QPON",
			"QUEBEC",
			"QUEST",
			"QVC",
			"RACING",
			"RADIO",
			"RAID",
			"RE",
			"READ",
			"REALESTATE",
			"REALTOR",
			"REALTY",
			"RECIPES",
			"RED",
			"REDSTONE",
			"REDUMBRELLA",
			"REHAB",
			"REISE",
			"REISEN",
			"REIT",
			"RELIANCE",
			"REN",
			"RENT",
			"RENTALS",
			"REPAIR",
			"REPORT",
			"REPUBLICAN",
			"REST",
			"RESTAURANT",
			"REVIEW",
			"REVIEWS",
			"REXROTH",
			"RICH",
			"RICHARDLI",
			"RICOH",
			"RIGHTATHOME",
			"RIL",
			"RIO",
			"RIP",
			"RMIT",
			"RO",
			"ROCHER",
			"ROCKS",
			"RODEO",
			"ROGERS",
			"ROOM",
			"RS",
			"RSVP",
			"RU",
			//"RUGBY",
			"RUHR",
			"RUN",
			"RW",
			"RWE",
			"RYUKYU",
			"SA",
			"SAARLAND",
			"SAFE",
			"SAFETY",
			"SAKURA",
			"SALE",
			"SALON",
			"SAMSCLUB",
			"SAMSUNG",
			"SANDVIK",
			"SANDVIKCOROMANT",
			"SANOFI",
			"SAP",
			"SARL",
			"SAS",
			"SAVE",
			"SAXO",
			"SB",
			"SBI",
			"SBS",
			"SC",
			"SCA",
			"SCB",
			"SCHAEFFLER",
			"SCHMIDT",
			"SCHOLARSHIPS",
			"SCHOOL",
			"SCHULE",
			"SCHWARZ",
			"SCIENCE",
			"SCJOHNSON",
			"SCOR",
			"SCOT",
			"SD",
			"SE",
			//"SEARCH",
			"SEAT",
			"SECURE",
			"SECURITY",
			"SEEK",
			"SELECT",
			"SENER",
			"SERVICES",
			"SES",
			"SEVEN",
			"SEW",
			"SEX",
			"SEXY",
			"SFR",
			"SG",
			"SH",
			"SHANGRILA",
			"SHARP",
			"SHAW",
			"SHELL",
			"SHIA",
			"SHIKSHA",
			"SHOES",
			"SHOP",
			"SHOPPING",
			"SHOUJI",
			"SHOW",
			"SHOWTIME",
			"SHRIRAM",
			"SI",
			"SILK",
			"SINA",
			"SINGLES",
			"SITE",
			"SJ",
			"SK",
			"SKI",
			"SKIN",
			"SKY",
			"SKYPE",
			"SL",
			"SLING",
			"SM",
			"SMART",
			"SMILE",
			"SN",
			"SNCF",
			"SO",
			"SOCCER",
			"SOCIAL",
			"SOFTBANK",
			"SOFTWARE",
			"SOHU",
			"SOLAR",
			"SOLUTIONS",
			"SONG",
			"SONY",
			"SOY",
			"SPACE",
			"SPIEGEL",
			//"SPORT",
			"SPOT",
			"SPREADBETTING",
			"SR",
			"SRL",
			"SRT",
			"ST",
			"STADA",
			"STAPLES",
			"STAR",
			"STARHUB",
			"STATEBANK",
			"STATEFARM",
			"STATOIL",
			"STC",
			"STCGROUP",
			"STOCKHOLM",
			"STORAGE",
			"STORE",
			"STREAM",
			"STUDIO",
			"STUDY",
			"STYLE",
			"SU",
			"SUCKS",
			"SUPPLIES",
			"SUPPLY",
			"SUPPORT",
			"SURF",
			"SURGERY",
			"SUZUKI",
			"SV",
			"SWATCH",
			"SWIFTCOVER",
			"SWISS",
			"SX",
			"SY",
			"SYDNEY",
			"SYMANTEC",
			"SYSTEMS",
			"SZ",
			"TAB",
			"TAIPEI",
			"TALK",
			"TAOBAO",
			"TARGET",
			"TATAMOTORS",
			"TATAR",
			"TATTOO",
			"TAX",
			"TAXI",
			"TC",
			"TCI",
			"TD",
			"TDK",
			"TEAM",
			"TECH",
			"TECHNOLOGY",
			"TEL",
			"TELECITY",
			"TELEFONICA",
			"TEMASEK",
			"TENNIS",
			"TEVA",
			"TF",
			"TG",
			"TH",
			"THD",
			"THEATER",
			"THEATRE",
			"TIAA",
			"TICKETS",
			"TIENDA",
			"TIFFANY",
			"TIPS",
			"TIRES",
			"TIROL",
			"TJ",
			"TJMAXX",
			"TJX",
			"TK",
			"TKMAXX",
			"TL",
			"TM",
			"TMALL",
			"TN",
			"TO",
			"TODAY",
			"TOKYO",
			"TOOLS",
			"TOP",
			"TORAY",
			"TOSHIBA",
			"TOTAL",
			"TOURS",
			"TOWN",
			"TOYOTA",
			"TOYS",
			"TR",
			"TRADE",
			"TRADING",
			"TRAINING",
			"TRAVEL",
			"TRAVELCHANNEL",
			"TRAVELERS",
			"TRAVELERSINSURANCE",
			"TRUST",
			"TRV",
			"TT",
			"TUBE",
			"TUI",
			"TUNES",
			"TUSHU",
			"TV",
			"TVS",
			"TW",
			"TZ",
			"UA",
			"UBANK",
			"UBS",
			"UCONNECT",
			"UG",
			"UK",
			"UNICOM",
			"UNIVERSITY",
			"UNO",
			"UOL",
			"UPS",
			"US",
			"UY",
			"UZ",
			"VA",
			"VACATIONS",
			"VANA",
			"VANGUARD",
			"VC",
			"VE",
			"VEGAS",
			"VENTURES",
			"VERISIGN",
			"VERSICHERUNG",
			"VET",
			"VG",
			"VI",
			"VIAJES",
			"VIDEO",
			"VIG",
			"VIKING",
			"VILLAS",
			"VIN",
			"VIP",
			"VIRGIN",
			"VISA",
			"VISION",
			"VISTA",
			"VISTAPRINT",
			"VIVA",
			"VIVO",
			"VLAANDEREN",
			"VN",
			"VODKA",
			"VOLKSWAGEN",
			"VOLVO",
			"VOTE",
			"VOTING",
			"VOTO",
			"VOYAGE",
			"VU",
			"VUELOS",
			"WALES",
			"WALMART",
			"WALTER",
			"WANG",
			"WANGGOU",
			"WARMAN",
			"WATCH",
			"WATCHES",
			"WEATHER",
			"WEATHERCHANNEL",
			"WEBCAM",
			"WEBER",
			"WEBSITE",
			"WED",
			"WEDDING",
			"WEIBO",
			"WEIR",
			"WF",
			"WHOSWHO",
			"WIEN",
			"WIKI",
			"WILLIAMHILL",
			"WIN",
			"WINDOWS",
			"WINE",
			"WINNERS",
			"WME",
			"WOLTERSKLUWER",
			"WOODSIDE",
			"WORK",
			"WORKS",
			"WORLD",
			"WOW",
			"WS",
			"WTC",
			"WTF",
			"XBOX",
			"XEROX",
			"XFINITY",
			"XIHUAN",
			"XIN",
			//"XN",
			"XXX",
			"XYZ",
			"YACHTS",
			"YAHOO",
			"YAMAXUN",
			"YANDEX",
			"YE",
			"YODOBASHI",
			"YOGA",
			"YOKOHAMA",
			"YOU",
			"YOUTUBE",
			"YT",
			"YUN",
			"ZA",
			"ZAPPOS",
			"ZARA",
			"ZERO",
			"ZIP",
			"ZIPPO",
			"ZM",
			"ZONE",
			"ZUERICH",
			"ZW"
	};	
	// possible combos for UrlValidator params
	long UrlValidatorCombos[] = {
			/*0*/		0,
			/*1*/		UrlValidator.ALLOW_2_SLASHES,
			/*2*/		UrlValidator.ALLOW_ALL_SCHEMES,
			/*3*/		UrlValidator.ALLOW_LOCAL_URLS,
			/*4*/		UrlValidator.NO_FRAGMENTS,
			/*5*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_ALL_SCHEMES,
			/*6*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_LOCAL_URLS,
			/*7*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.NO_FRAGMENTS,
			/*8*/		UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS,
			/*9*/		UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.NO_FRAGMENTS,
			/*10*/		UrlValidator.ALLOW_LOCAL_URLS + UrlValidator.NO_FRAGMENTS,
			/*11*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS,
			/*12*/		UrlValidator.ALLOW_2_SLASHES +UrlValidator.ALLOW_ALL_SCHEMES+UrlValidator.NO_FRAGMENTS,
			/*13*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_LOCAL_URLS + UrlValidator.NO_FRAGMENTS,
			/*14*/		UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS + UrlValidator.NO_FRAGMENTS,
			/*15*/		UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS + UrlValidator.NO_FRAGMENTS
			};

	   String lowercase = "abcdefghijklmnopqrstuvwxyz";
	   String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   String numbers = "0123456789";
	   String punctuation = "`~!@#$%^&*()-_+=\\|{}[]';:/?.,><\""; 
	   String URI_permitted_chars = "!$&'()*+,;=";
	   
	   Random random = new Random();
	   
	    UrlValidator urlVal = new UrlValidator(null, null, UrlValidatorCombos[11]);//null, null, UrlValidator.ALLOW_ALL_SCHEMES );
	
	
	// ALLOW_2_SLASHES = 1, 5, 6, 7, 11, 12, 13, 15
	// ALLOW_ALL_SCHEMES = 2, 5, 8, 9, 11, 12, 14, 15
	// ALLOW_LOCAL_URLS = 3, 6, 8, 10, 11, 13, 14, 15
	// NO_FRAGMENTS = 4, 7, 9, 10, 12, 13, 14, 15
	
	
	 public UrlValidatorTest2(String testName) {
		      super(testName);
		   }
	
	 
	 public void testIsValid()
	 {
		 
		 System.out.println("Beginning Random Tests");
	// variables
		 // Init various combos of UrlValidator
		   UrlValidator[] urlValArr = new UrlValidator[UrlValidatorCombos.length];
		   
		   for(int i = 0; i < UrlValidatorCombos.length; i++)
			   urlValArr[i] = new UrlValidator(null, null, UrlValidatorCombos[i]);
	
		   
		   ResultPair SchemePair = new ResultPair("http://", true);
		   ResultPair AuthorityPair = new ResultPair("www.google.com", true);
		   		ResultPair UserInfoPair = new ResultPair("", true);
		   			ResultPair UserNamePair = new ResultPair("", true);
		   			ResultPair PasswordPair = new ResultPair("", true);
		   		ResultPair HostPair = new ResultPair("www.google.com", true);
		   		ResultPair PortPair = new ResultPair("", true);
		   		
		   		
		   ResultPair PathPair = new ResultPair("", true);
		   ResultPair QueryPair = new ResultPair("", true);
		   ResultPair FragmentPair = new ResultPair("", true);
		  
		   String url = "";
			 
		   boolean result = true;
		   boolean expected = true;
		    
		   boolean has_all_schemes = false;
		   boolean has_2_slashes = false;
		   boolean has_local_url = false;
		   boolean has_fragment = false;
		   
		   //XXwxm://96.41.105.170:48716/TwzC/XuVR/mPQzIBnqRKvAd8ybtGXkeAzHnpBQIeP/5JZ4oSUg6kqk0sTOqb9ZH/myXbiPwIq2eI3SV0m/JFRnTG6Je8mI/dwYAO5SnZWsLW2B3pSZl3fP/Jsqgy3Mv1VWhgyDuC7tewxvHz/
		   assertTrue(urlValArr[2].isValid("XXwxm://96.41.105.170:48716/TwzC/XuVR/mPQzIBnqRKvAd8ybtGXkeAzHnpBQIeP/5JZ4oSUg6kqk0sTOqb9ZH/myXbiPwIq2eI3SV0m/JFRnTG6Je8mI/dwYAO5SnZWsLW2B3pSZl3fP/Jsqgy3Mv1VWhgyDuC7tewxvHz/"));
		   
		   
		   for(int i = 0; i < 500000; i++)
		   {
					     
			   // start output of test #
			   System.out.println("");
			   System.out.print(Integer.toString(i) + ": ");
			   
			   
			   SchemePair.item = "http://";
			   SchemePair.valid = true;
			   AuthorityPair.item = "www.google.com";
			   AuthorityPair.valid = true;
			   PathPair.item = "/";
			   PathPair.valid = true;
			   QueryPair.item = "";
			   QueryPair.valid = true;
			   FragmentPair.item = "";
			   FragmentPair.valid = true;
			   
			   
			// get scheme
			if(testSchemes) {
			   if(random.nextBoolean() == true)
			   {
				   SchemePair = generateScheme();
				 //  SchemePair.valid = !SchemePair.valid;
			//	   System.out.println("~~~~ SchemePair.valid: " + Boolean.toString(SchemePair.valid));
				   has_all_schemes = true;
			   }
			   else
			   {
			//	   System.out.println("$$$$ Valid Scheme");
				   SchemePair = generateValidScheme();
				   has_all_schemes = false;
			   }
			}
/*			else
			{
				SchemePair.item = "http://";
				SchemePair.valid = true;
			}
*/		
			if(SchemePair.item.length() >= 3 && !SchemePair.item.substring(SchemePair.item.length() - 3).contains( "://"))
				SchemePair.valid = false;
			
			if(SchemePair.item.length() < 4)
				SchemePair.valid = false;
			
			
			
			// get authority
			if(testAuthorities)
			{
				UserNamePair.item = "";
				UserNamePair.valid = true;
				PasswordPair.item = "";
				PasswordPair.valid = true;
				PortPair.item = "";
				PortPair.valid = true;
				
				// generate userinfo ?
				int genUserInfo = random.nextInt(100);
				if(genUserInfo < 15)  // % of time to fire
				{
					System.out.println("genUserInfo: " + Integer.toString(genUserInfo));
					// generate username ?
					if(random.nextInt(100) < 5)
						UserNamePair = generateUserName();
					
					
					if(UserNamePair.item.length() < 1)
						UserNamePair.valid = false;
					
					// generate password ?
					if(random.nextBoolean() == true)
						PasswordPair = generatePassword();
					
					UserInfoPair.item = UserNamePair.item + ":" +PasswordPair.item;
					UserInfoPair.valid = UserNamePair.valid && PasswordPair.valid;
					
					if(UserInfoPair.item.length() > 1)
						UserInfoPair.item += "@";
					else
					{
						UserInfoPair.item = "";
						UserInfoPair.valid = true;
					}

				}
				
				int genTypeHost = random.nextInt(3); // 0->ipv4; 1->ipv6; 2->domain name
				HostPair = generateHost(genTypeHost);
				
				// ipv6 can't have userinfo
				if(genTypeHost == 1 && UserInfoPair.item.length() > 0)
					HostPair.valid = false;
					
				AuthorityPair.item = UserInfoPair.item + HostPair.item;
				AuthorityPair.valid = UserInfoPair.valid && HostPair.valid;
				
				if(random.nextBoolean() == true)
					PortPair = generatePort();
				
				if(PortPair.item.length() > 0)
				{
					AuthorityPair.item = AuthorityPair.item + ":" + PortPair.item;
					AuthorityPair.valid = AuthorityPair.valid && PortPair.valid;
				}
				
			/*	// check if file: scheme
				if(SchemePair.item.contains("file"))
					AuthorityPair.valid = true;
				*/
			}
			else
			{

				AuthorityPair.item = "www.google.com";
				AuthorityPair.valid = true;
			}
			
			// get path
			if(testPaths)
			{
				PathPair.item = "";
				PathPair.valid = true;
				has_2_slashes = false;
				
				if(random.nextBoolean() == true)
					PathPair = generatePath();
				
			//	if(PathPair.item.length() > 0 && PathPair.item.charAt(0) == '/')
			//		PathPair.valid = false;
				
				if(PathPair.item.length() > 0)
					PathPair.item = "/" + PathPair.item;
				
				if(PathPair.item.length() > 0 && PathPair.item.contains("//"))
					has_2_slashes = true;
			}
			else
			{
				PathPair.item = "/";
				PathPair.valid = true;
			}
			   
			// get query
			if(testQueries)
			{
				QueryPair.item = "";
				QueryPair.valid = true;
				
				if(random.nextBoolean() == true)
					QueryPair = generateQuery();
				
				if(QueryPair.item.length() > 0)
					QueryPair.item = "?" + QueryPair.item;
			}
			else
			{
				QueryPair.item = "";
				QueryPair.valid = true;
			}
			   
			
			// get fragment
			if(testFragments)
			{
				FragmentPair.item = "";
				FragmentPair.valid = true;
				has_fragment = false;   // fragment is a little different, NO_FRAGMENT disables fragment support 
				
				if(random.nextBoolean() == true)
					FragmentPair = generateFragment();
				
				if(FragmentPair.item.length() > 0)
				{
					FragmentPair.item = "#" + FragmentPair.item;
					has_fragment = true;
				}
				else
				{
					if(random.nextInt(100) < 10)
					{
						FragmentPair.item = "#";
						FragmentPair.valid = true;
						has_fragment = true;
					}
					else
					{
						has_fragment = false;
					}
				}
				
			}
			else
			{
				FragmentPair.item = "";
				FragmentPair.valid = true;
			}
			
			 // build url
			   url = SchemePair.item + AuthorityPair.item  + PathPair.item + QueryPair.item + FragmentPair.item;
		   
		   
		   System.out.println("url: " + url);
		   
		   // test each combo of UrlValidator
		   for(int j = 0; j < UrlValidatorCombos.length; j++)
	   		{
	   		// ALLOW_2_SLASHES = 1, 5, 6, 7, 11, 12, 13, 15
	   		// ALLOW_ALL_SCHEMES = 2, 5, 8, 9, 11, 12, 14, 15
	   		// ALLOW_LOCAL_URLS = 3, 6, 8, 10, 11, 13, 14, 15
	   		// NO_FRAGMENTS = 4, 7, 9, 10, 12, 13, 14, 15
	   			
	   			expected = SchemePair.valid && AuthorityPair.valid && PathPair.valid && QueryPair.valid && FragmentPair.valid;
	   			
	   			switch(j) {
	   			// 2 slashes not allowed, all schemes not allowed, local urls not allowed, fragments allowed
	   			case 0:
	   				expected = expected && !has_2_slashes && !has_all_schemes && !has_local_url;
	   			break;
	   			// 2 slashes allowed, all schemes not allowed, local urls not allowed, fragments allowed
	   			case 1:
	   				expected = expected && !has_all_schemes && !has_local_url;
	   			break;
	   			// 2 slashes not allowed, all schemes allowed, local urls not allowed, fragments allowed
	   			case 2:
	   				expected = expected && !has_2_slashes && !has_local_url;
	   			break;
	   			// 2 slashes not allowed, all schemes not allowed, local url allowed, fragments allowed
	   			case 3:
	   				expected = expected && !has_2_slashes && !has_all_schemes;
	   			break;
	   			// 2 slashes not allowed, all schemes not allowed, local urls not allowed, fragments not allowed
	   			case 4:
	   				expected = expected && !has_2_slashes & !has_all_schemes && !has_local_url && !has_fragment;
	   			break;
	   			// 2 slashes allowed, all schemes allowed, local urls not allowed, fragments allowed
	   			case 5:
	   				expected = expected && !has_local_url;
	   			break;
	   			// 2 slashes allowed, all schemes not allowed, local urls allowed, fragments allowed
	   			case 6:
	   				expected = expected && !has_all_schemes;
	   			break;
	   			// 2 slashes allowed, all schemes not allowed, local urls not allowed, fragments not allowed
	   			case 7:
	   				expected = expected && !has_all_schemes && !has_local_url && !has_fragment;
	   			break;
	   			// 2 slashes not allowed, all schemes allowed, local urls allowed, fragments allowed
	   			case 8:
	   				expected = expected && !has_2_slashes;
	   			break;
	   			// 2 slashes not allowed, all schemes allowed, local urls not allowed, fragments not allowed
	   			case 9:
	   				expected = expected && !has_2_slashes && !has_local_url && !has_fragment;
	   			break;
	   			// 2 slashes not allowed, all schemes not allowed, local urls allowed, fragments not allowed
	   			case 10:
	   				expected = expected && !has_2_slashes && !has_all_schemes && !has_fragment;
	   			break;
	   			// 2 slashes allowed, all schemes allowed, local urls allowed, fragments allowed
	   			case 11:
	   				//expected = expected;
	   			break;
	   			// 2 slashes allowed, all schemes allowed, local urls not allowed, fragments not allowed
	   			case 12:
	   				expected = expected && !has_local_url && !has_fragment;
	   			break;
	   			// 2 slashes allowed, all schemes not allowed, local urls allowed, no fragments allowed
	   			case 13:
	   				expected = expected && !has_all_schemes && !has_fragment;
	   			break;
	   			// 2 slashes not allowed, all schemes allowed, local urls allowed, fragments not allowed
	   			case 14:
	   				expected = expected && !has_2_slashes && !has_fragment;
	   			break;
	   			// 2 slashes allowed, all schemes allowed, local urls allowed, fragments not allowed
	   			case 15:
	   				expected = expected && !has_fragment;
	   			break;
	   			
	   			default:
	   				System.out.println("**** ERROR: only 16 possible combinations of UrlValidator state)");
	   				assertTrue(false);
	   			}
	   			
	   			
	   			System.out.print(Integer.toString(j) + " - ");
	   			
	   			result = urlValArr[j].isValid(url);
	   			if(result != expected)
	   			{
	   				System.out.println("** Expected: " + Boolean.toString(expected) + "  Result: " + Boolean.toString(result));
	   				
	   				if(expected == SchemePair.valid)
	   					System.out.print("SchemePair: " + Boolean.toString(SchemePair.valid));
	   				if(expected == AuthorityPair.valid)
	   				{
	   					System.out.print(" AuthorityPair: " + Boolean.toString(AuthorityPair.valid));
	   					System.out.print(" SchemePair: " + Boolean.toString(SchemePair.valid));
	   					System.out.print(" UserInfoPair: " + Boolean.toString(UserInfoPair.valid));
	   					System.out.print(" HostPair: " + Boolean.toString(HostPair.valid));
	   					System.out.print(" PortPair: " + Boolean.toString(PortPair.valid));
	   		   			
	   		   			
	   				}
	   				if(expected == PathPair.valid)
	   					System.out.print(" PathPair: " + Boolean.toString(PathPair.valid));
	   				if(expected == QueryPair.valid)
	   					System.out.print(" QueryPair: " + Boolean.toString(QueryPair.valid));
	   				if(expected == FragmentPair.valid)
	   					System.out.print(" FragmentPair: " + Boolean.toString(FragmentPair.valid));
	   				System.out.println("");
	   				
	   				boolean boolVals[] = {has_2_slashes, has_all_schemes, has_local_url, has_fragment};
	   				outputTestInfo(url, SchemePair, AuthorityPair, PathPair, QueryPair, FragmentPair, j, boolVals);
	   			}
	   			
	   			// ignore invalid port values
	   			if(!(result == true && PortPair.valid == false))
	   				assertTrue(result == expected);
	   		}	}   
		   
		   
		   // static tests
		   
		  
		   
		   for(int i = 0; i < urlValArr.length; i++)
		   {
			   System.out.println("Static Tests");
			   assertTrue(urlValArr[i].isValid("") == false);
			   assertTrue(urlValArr[i].isValid(null) == false);
			   assertTrue(urlValArr[i].isValid(randoString(uppercase + lowercase + numbers, 10000)) == false);
			   assertTrue(urlValArr[i].isValid("http://www.google.com") == true);
			   assertTrue(urlValArr[i].isValid("??://www.google.com#?asdfsa###") == false);
			   assertTrue(urlValArr[i].isValid("http://[ffff:FFFF:0000:0000:abcd:efahb:1234:9999]") == false);
			//   urlValArr[i].isValid("file:///C:/users");
			 //  urlValArr[i].isValid("`~!---- ------------ --");
			   
			 //  urlValArr[i].isValid("file:dosbox.com/");
			   assertTrue( urlValArr[i].isValid("file://asdfjkl:#") == false);
			   assertTrue(urlValArr[i].isValid("file:dosbox.com/") == false);
			   if(i == 2 || i == 5 || i == 8 || i == 9 || i == 11 || i == 12 || i == 14 || i == 15)
			   {
				   assertTrue(urlValArr[i].isValid("file://asdfjkl")==true);
				   
			   }
			   else
			   {
				   assertTrue(urlValArr[i].isValid("file://asdfjkl")==false);

			   }
			
		   }
	 }
	 public void outputTestParams(int testParams)
	   {
	  		// ALLOW_2_SLASHES = 1, 5, 6, 7, 11, 12, 13, 15
	  		// ALLOW_ALL_SCHEMES = 2, 5, 8, 9, 11, 12, 14, 15
	  		// ALLOW_LOCAL_URLS = 3, 6, 8, 10, 11, 13, 14, 15
	  		// NO_FRAGMENTS = 4, 7, 9, 10, 12, 13, 14, 15
		   
		   switch(testParams)
		   {
		   case 0:
			   printTestParams(false, false, false, false);
			   break;
		   case 1:
			   printTestParams(true, false, false, false);
			   break;
		   case 2:
			   printTestParams(false, true, false, false);
			   break;
		   case 3:
			   printTestParams(false, false, true, false);
			   break;
		   case 4:
			   printTestParams(false, false, false, true);
			   break;
		   case 5:
			   printTestParams(true, true, false, false);
			   break;
		   case 6:
			   printTestParams(true, false, true, false);
			   break;
		   case 7:
			   printTestParams(true, false, false, true);
			   break;
		   case 8:
			   printTestParams(false, true, true, false);
			   break;
		   case 9:
			   printTestParams(false, true, false, true);
			   break;
		   case 10:
			   printTestParams(false, false, true, true);
			   break;
		   case 11:
			   printTestParams(true, true, true, false);
			   break;
		   case 12:
			   printTestParams(true, true, false, true);
			   break;
		   case 13:
			   printTestParams(true, false, true, true);
			   break;
		   case 14:
			   printTestParams(false, true, true, true);
			   break;
		   case 15:
			   printTestParams(true, true, true, true);
			   break;
		   }
	   }
	   
	   public void printTestParams(boolean slashes, boolean schemes, boolean localUrls, boolean noFragments)
	   {
		   System.out.println("Test Params: ALLOW_2_SLASHES: " + Boolean.toString(slashes) + " ALLOW_ALL_SCHEMES: " + Boolean.toString(schemes) + " ALLOW_LOCAL_URLS: " + Boolean.toString(localUrls) + " NO_FRAGMENTS: " + Boolean.toString(noFragments));
	   }
	   
	   // output test info for debug
	   public void outputTestInfo(String url, ResultPair SchemePair, ResultPair AuthorityPair, ResultPair PathPair, ResultPair QueryPair, ResultPair FragmentPair, int testParams, boolean[] boolVals)
	   {
		   System.out.println("---------- TEST FAILED -----------");
		   System.out.println("Test Params: " + Integer.toString(testParams));
		   System.out.println("Boolean Values: has_2_slashes: " + Boolean.toString(boolVals[0]) + " has_all_schemes: " + Boolean.toString(boolVals[1]) + " has_local_url: " + Boolean.toString(boolVals[2]) + " has_fragment: " + Boolean.toString(boolVals[3]));
		   
		   outputTestParams(testParams);
		   System.out.println("url: " + url);
		   
		   // check scheme
		   
		   //remove :// from end if present
		   String scheme = SchemePair.item;
		   if(scheme.substring(scheme.length() - 3).contains("://"))
			   scheme = scheme.substring(0, scheme.length() - 3);
		   
		   if(urlVal.isValidScheme(scheme) != SchemePair.valid)
			   System.out.println("SchemePair: "+ SchemePair.valid + " -- " + scheme );
		   
		   // check authority
		   if(urlVal.isValidAuthority(AuthorityPair.item)!= AuthorityPair.valid )
		   		System.out.println("AuthorityPair: "+ AuthorityPair.valid + " -- " + AuthorityPair.item );
		   
		   // check path
		   if(urlVal.isValidPath(PathPair.item) != PathPair.valid )
			   System.out.println("PathPair: "+ PathPair.valid + " -- " + PathPair.item );
		   
		   // check query
		   if(urlVal.isValidQuery(QueryPair.item) != QueryPair.valid )
			   System.out.println("QueryPair: "+ QueryPair.valid + " -- " + QueryPair.item );
		   
		   // check fragment
		   if(urlVal.isValidFragment(FragmentPair.item) != FragmentPair.valid )
			   System.out.println("FragmentPair: "+ FragmentPair.valid + " -- " + FragmentPair.item );
		   
		   System.out.println("----------------------------------");
	   }
	 
	 
	 
	 // generate valid or invalid scheme
	   ResultPair generateScheme()
	   {

		   boolean schemeValid = true;

		   
		   // create a valid scheme  
		   String scheme = randoString(uppercase + lowercase + numbers, 20); 
		   String endScheme = randoString("+-.", 1);
		   
		   // if scheme is only +, - or . then it's invalid
		   if(scheme.length() == 0 && endScheme.length() > 0)
			   schemeValid = false;
		   
		   

		   
		   if(random.nextInt(100) < 25)
		   {
			   schemeValid = false;
			   
			   String appendage = randoString(uppercase + lowercase + numbers, 25);
			   
			   // prevent appendage from being valid
			   while(appendage.contains("://"))
				   appendage = randoString(uppercase + lowercase + numbers, 25);
			   
			   scheme += appendage;
		   }
		   else
		   {
			   // "://" Is and invalid scheme
			   if(scheme.length() == 0)
				   schemeValid = false;
			   
			   scheme += "://";
		   }   
		   
		   // invalid if first char is number
		   if(scheme.length() > 0 && numbers.contains(Character.toString(scheme.charAt(0))))
		   {
			  // System.out.println("First char is a digit !!!!!");
			   schemeValid = false;
		   }
		   
		//   System.out.println("schemeValid: " + Boolean.toString(schemeValid));
		   return new ResultPair(scheme, schemeValid);
	   }
	   
	   ResultPair generateValidScheme()
	   {
		   int index = random.nextInt(validSchemes.length);
		   return new ResultPair(validSchemes[index] + "://", true);
	   }
	   // generates random string from validChars
	   public String randoString(String validChars, int limit)
	   {
		   String retString = "";
		   
		   Random random = new Random();
		   
		   int numChars = random.nextInt(limit + 1);
		   int randPos = -1;
		   
		   for(int i = 0; i < numChars; i++)
		   {
			   randPos = random.nextInt(validChars.length());
			   retString += Character.toString(validChars.charAt(randPos));
			   
		   }
		   
		  
		   return retString;
	   }
	   
	   // generate username
	   public ResultPair generateUserName()
	   {
		   String username = "";
		   boolean usernameValid = true;
		   
		   username = randoString(uppercase + lowercase + numbers + URI_permitted_chars, 63);
		   
		   
		   
		   return new ResultPair(username, usernameValid);
	   }
	   
	   // generate password
	   public ResultPair generatePassword()
	   {
		   String password = "";
		   boolean passwordValid = true;
		   
		   password = randoString(uppercase + lowercase + numbers + URI_permitted_chars, 63);

		   return new ResultPair(password, passwordValid);
	   }
	   
	   
	   // generate host
	   public ResultPair generateHost(int type)
	   {
		   String host = "";
		   boolean hostValid = true;
		   
		 //switch(4) 
		   switch(type)
		   
		   {
		   // valid ip4
		   	case 0: 
			   host += generateIP4();
			   break;
		   	case 1:
		   	// valid ip6 -- note ip6 cannot have user info so it is removed
		   		hostValid = true;
		   		host = "[" + generateIP6() + "]";
		   		
		   		int randoInvalidBrackets = random.nextInt(100);
		   		
		   		if(randoInvalidBrackets < 3)
		   		{
		   			host = randoString(numbers + uppercase + lowercase, 1) + host.substring(1);
		   			hostValid = false;
		   		}
		   		else if(randoInvalidBrackets < 6)
		   		{
		   			host =  host.substring(0, host.length() - 2) + randoString(numbers + uppercase + lowercase, 1);
		   			hostValid = false;
		   		}
		   		else if(randoInvalidBrackets < 9)
		   		{
		   			host = randoString(numbers + uppercase + lowercase, 1) + host.substring(1, host.length() - 2) + randoString(numbers + uppercase + lowercase, 1);
		   			hostValid = false;
		   		}
		   			
		   		
		   		
		   		break;
		   	
		   	// default generate domain string (may be valid or invalid)
		   	default:
		   		ResultPair temp = generateDomain();
		   		host += temp.item;
		   		hostValid = hostValid && temp.valid;
		   		
		   			
		   }
		   
		   
		   return new ResultPair(host, hostValid);
	   }
	   
	   // generate valid ip4 address
	   String generateIP4()
	   {
		   String IP4 = "";
		   
			   // generate valid ip4 address
		   for(int i = 0; i < 4; i++)
		   {
			   IP4 += Integer.toString(random.nextInt(256));
				   
			   if(i < 3) IP4 += ".";
		   }
		 
		   return IP4;
	   }
	   
	   
	   // generate valid ip6 address
	   String generateIP6()
	   {
		   String ip6 = "";
		   
		   // generate valid ip6 address
		   for(int i = 0; i < 8; i++)
		   {
			   ip6 += Integer.toHexString((random.nextInt(65536)));
			   
			   if(i < 7) ip6 += ":";
		   }
		   
		   return ip6;
	   }
	   
	   
	   // return valid top level domain from list
	  String generateValidTLD()
	  {
		  int index = random.nextInt(validTLDs.length);
		  return validTLDs[index];
	  }
	   
	   // generate valid domain
	  ResultPair generateDomain()
	   {
		   String domain = "";
		   boolean domainValid = true;
		   
		   int parts = random.nextInt(10);
		   
		   // check if at least one part
		   if(parts == 0) 
		   {
			//   System.out.println("false from parts == 0");
			   domainValid = false;
		   }
		   
		   int segmentLength = 69;
		   String temp = "";
		   
		   for(int i = 0; i < parts; i++)
		   {
			   temp = randoString(uppercase + lowercase + numbers + "-", segmentLength);
			 //  System.out.println("temp.length(): " + Integer.toString(temp.length()));
				domain += temp;
			   // check length
			   if(temp.length() == 0 || temp.length() > 63)
			   {
				   domainValid = false;
				//   System.out.println("false from temp.length();: " + Integer.toString(temp.length()));
			   }
			   
			   // check if '-' at start or end
			   if((temp.length() > 0) && ((temp.charAt(0) == '-')  || (temp.charAt(temp.length() - 1) == '-')))
			   {
				   domainValid = false;
				  // System.out.println("false from '-'");
			   }
			  
			   domain += ".";
		   }
		   
		 if(domain.length() == 0)
		 {
			 domain += ".";
			 domainValid = false;
		 }

		   
			domain += generateValidTLD();
			 
			 
			 
		   if(domain.length() > 253 || domain.length() == 0 || domain.contains("..") || !domain.contains("."))
		   {
			 //  System.out.println("false from total length or .. or not .");
			   domainValid = false;
		   }
		  
		  // System.out.println(domain);
		   return new ResultPair(domain, domainValid);
	   }
	   
	  
	  // generate port
	  public ResultPair generatePort()
	  {
		  String port = "";
		  boolean portValid = true;
		  
		  int portNum = random.nextInt(80000);
		  portNum -= 5000;
		  
		  if(portNum < 0 || portNum > 65535)
			  portValid = false;
		  
		  port = Integer.toString(portNum);
		  
		  // randomly add chars
		  if(random.nextInt(100) < 2)
		  {
			  for(int i = 0; i < port.length(); i++)
			  {
				  if(random.nextInt(100) < 20)
				  {
					  	port = port.substring(0, i) + randoString(uppercase + lowercase + URI_permitted_chars, 1) + port.substring(i + 1);
					  	portValid = false;
				  }  
			  }
		  }
		  
		  return new ResultPair(port, portValid);
	  }
	   
	  // generate paths
	  public ResultPair generatePath()
	  {
		  String path = "";
		  boolean pathValid = true;
		  
		   int numberSegments = random.nextInt(13);
		   
		   for(int i = 0; i < numberSegments; i++)
		   {
			   path += randoString(uppercase + lowercase + numbers /*+ URI_permitted_chars /*+ ":@"*/, 30);
		   
			   if(random.nextInt(10) > 0)
				   path += "/";
		   }
		   
		   // path cannot start with '//' (will have / added before it)
	//	   if(path.length() >= 1 && path.charAt(0) == '/')
	//		   pathValid = false;
		   if(path.length() == 1 && checkIfAllSlashes(path))
			   pathValid = false;

		   if(path.length() == 0)
			   pathValid = true;
 		  
		  return new ResultPair(path, pathValid);
	  }
	  
	  public boolean checkIfAllSlashes(String str)
	  {
		  for(int i = 0; i < str.length(); i++)
		  {
			  if(str.charAt(i) != '/')
				  return false;
		  }
		  return true;
	  }
	  
	  
	  // generate query
	  public ResultPair generateQuery()
	  {
		  String query = "";
		  boolean queryValid = true;
		  
		  String queryString = uppercase + lowercase + URI_permitted_chars + numbers + ":@/?";
		  
		  if(random.nextBoolean() == true)
			  queryString = queryString + " ";
		  
		  query = randoString(queryString , 130);
		
		  if(query.contains(" "))
			  queryValid = false;
		  
		  
		  return new ResultPair(query, queryValid);
	  }
	  
	  
	  public ResultPair generateFragment()
	  {
		  String fragment = "";
		  boolean fragmentValid = true;
		  
		  String fragmentString = uppercase  + lowercase + URI_permitted_chars + numbers + ":@/?";
		  
		  if(random.nextBoolean() == true)
			  fragmentString = fragmentString + "# \\\"\';:~`$%^&*()-_+=|{}[]<>,.";
		  
		  fragment = randoString(fragmentString, 130);
		  
		  //if(fragment.contains(""))
		//	  fragmentValid = false;
		  
		  return new ResultPair(fragment, fragmentValid);
	  }
	  
}
