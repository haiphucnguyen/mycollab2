/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Class keep all timezones of system
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TimezoneMapper {
    private static Logger LOG = LoggerFactory.getLogger(TimezoneMapper.class);
    public static Map<String, TimezoneExt> timeMap;

    public static final String AREAS[] = new String[]{"UTC", "Africa", "America",
            "Atlantic", "Antarctica", "Asia", "Australia", "Europe", "Indian",
            "Pacific"};

    static {
        timeMap = new LinkedHashMap<>();

        timeMap.put("3", new TimezoneExt("3", "Pacific", "Pacific/Midway"));
        timeMap.put("4", new TimezoneExt("4", "Pacific", "Pacific/Niue"));
        timeMap.put("5", new TimezoneExt("5", "Pacific", "Pacific/Pago_Pago"));
        timeMap.put("6", new TimezoneExt("6", "Pacific", "Pacific/Samoa"));
        timeMap.put("8", new TimezoneExt("8", "America", "America/Adak"));
        timeMap.put("9", new TimezoneExt("9", "America", "America/Atka"));
        timeMap.put("12", new TimezoneExt("12", "Pacific", "Pacific/Fakaofo"));
        timeMap.put("13", new TimezoneExt("13", "Pacific", "Pacific/Honolulu"));
        timeMap.put("14", new TimezoneExt("14", "Pacific", "Pacific/Johnston"));
        timeMap.put("15", new TimezoneExt("15", "Pacific", "Pacific/Rarotonga"));
        timeMap.put("16", new TimezoneExt("16", "Pacific", "Pacific/Tahiti"));
        timeMap.put("20", new TimezoneExt("20", "Pacific", "Pacific/Marquesas"));
        timeMap.put("22", new TimezoneExt("22", "America", "America/Anchorage"));
        timeMap.put("23", new TimezoneExt("23", "America", "America/Juneau"));
        timeMap.put("24", new TimezoneExt("24", "America", "America/Nome"));
        timeMap.put("25", new TimezoneExt("25", "America", "America/Sitka"));
        timeMap.put("26", new TimezoneExt("26", "America", "America/Yakutat"));
        timeMap.put("28", new TimezoneExt("28", "Pacific", "Pacific/Gambier"));
        timeMap.put("32", new TimezoneExt("32", "America", "America/Dawson"));
        timeMap.put("33", new TimezoneExt("33", "America", "America/Ensenada"));
        timeMap.put("34", new TimezoneExt("34", "America", "America/Los_Angeles"));
        timeMap.put("35", new TimezoneExt("35", "America", "America/Metlakatla"));
        timeMap.put("36", new TimezoneExt("36", "America", "America/Santa_Isabel"));
        timeMap.put("37", new TimezoneExt("37", "America", "America/Tijuana"));
        timeMap.put("38", new TimezoneExt("38", "America", "America/Vancouver"));
        timeMap.put("39", new TimezoneExt("39", "America", "America/Whitehorse"));
        timeMap.put("46", new TimezoneExt("46", "Pacific", "Pacific/Pitcairn"));
        timeMap.put("51", new TimezoneExt("51", "America", "America/Boise"));
        timeMap.put("52", new TimezoneExt("52", "America", "America/Cambridge_Bay"));
        timeMap.put("53", new TimezoneExt("53", "America", "America/Chihuahua"));
        timeMap.put("54", new TimezoneExt("54", "America", "America/Dawson_Creek"));
        timeMap.put("55", new TimezoneExt("55", "America", "America/Denver"));
        timeMap.put("56", new TimezoneExt("56", "America", "America/Edmonton"));
        timeMap.put("57", new TimezoneExt("57", "America", "America/Hermosillo"));
        timeMap.put("58", new TimezoneExt("58", "America", "America/Inuvik"));
        timeMap.put("59", new TimezoneExt("59", "America", "America/Mazatlan"));
        timeMap.put("60", new TimezoneExt("60", "America", "America/Ojinaga"));
        timeMap.put("61", new TimezoneExt("61", "America", "America/Phoenix"));
        timeMap.put("62", new TimezoneExt("62", "America", "America/Shiprock"));
        timeMap.put("63", new TimezoneExt("63", "America", "America/Yellowknife"));
        timeMap.put("75", new TimezoneExt("75", "America", "America/Bahia_Banderas"));
        timeMap.put("76", new TimezoneExt("76", "America", "America/Belize"));
        timeMap.put("77", new TimezoneExt("77", "America", "America/Cancun"));
        timeMap.put("78", new TimezoneExt("78", "America", "America/Chicago"));
        timeMap.put("79", new TimezoneExt("79", "America", "America/Costa_Rica"));
        timeMap.put("80", new TimezoneExt("80", "America", "America/El_Salvador"));
        timeMap.put("81", new TimezoneExt("81", "America", "America/Guatemala"));
        timeMap.put("82", new TimezoneExt("82", "America", "America/Indiana/Knox"));
        timeMap.put("83", new TimezoneExt("83", "America", "America/Indiana/Tell_City"));
        timeMap.put("84", new TimezoneExt("84", "America", "America/Knox_IN"));
        timeMap.put("85", new TimezoneExt("85", "America", "America/Managua"));
        timeMap.put("86", new TimezoneExt("86", "America", "America/Matamoros"));
        timeMap.put("87", new TimezoneExt("87", "America", "America/Menominee"));
        timeMap.put("88", new TimezoneExt("88", "America", "America/Merida"));
        timeMap.put("89", new TimezoneExt("89", "America", "America/Mexico_City"));
        timeMap.put("90", new TimezoneExt("90", "America", "America/Monterrey"));
        timeMap.put("91", new TimezoneExt("91", "America", "America/North_Dakota/Beulah"));
        timeMap.put("92", new TimezoneExt("92", "America", "America/North_Dakota/Center"));
        timeMap.put("93", new TimezoneExt("93", "America", "America/North_Dakota/New_Salem"));
        timeMap.put("94", new TimezoneExt("94", "America", "America/Rainy_River"));
        timeMap.put("95", new TimezoneExt("95", "America", "America/Rankin_Inlet"));
        timeMap.put("96", new TimezoneExt("96", "America", "America/Regina"));
        timeMap.put("97", new TimezoneExt("97", "America", "America/Resolute"));
        timeMap.put("98", new TimezoneExt("98", "America", "America/Swift_Current"));
        timeMap.put("99", new TimezoneExt("99", "America", "America/Tegucigalpa"));
        timeMap.put("100", new TimezoneExt("100", "America", "America/Winnipeg"));
        timeMap.put("109", new TimezoneExt("109", "Pacific", "Pacific/Easter"));
        timeMap.put("110", new TimezoneExt("110", "Pacific", "Pacific/Galapagos"));
        timeMap.put("115", new TimezoneExt("115", "America", "America/Atikokan"));
        timeMap.put("116", new TimezoneExt("116", "America", "America/Bogota"));
        timeMap.put("117", new TimezoneExt("117", "America", "America/Cayman"));
        timeMap.put("118", new TimezoneExt("118", "America", "America/Coral_Harbour"));
        timeMap.put("119", new TimezoneExt("119", "America", "America/Detroit"));
        timeMap.put("120", new TimezoneExt("120", "America", "America/Fort_Wayne"));
        timeMap.put("121", new TimezoneExt("121", "America", "America/Grand_Turk"));
        timeMap.put("122", new TimezoneExt("122", "America", "America/Guayaquil"));
        timeMap.put("123", new TimezoneExt("123", "America", "America/Havana"));
        timeMap.put("124", new TimezoneExt("124", "America", "America/Indiana/Indianapolis"));
        timeMap.put("125", new TimezoneExt("125", "America", "America/Indiana/Marengo"));
        timeMap.put("126", new TimezoneExt("126", "America", "America/Indiana/Petersburg"));
        timeMap.put("127", new TimezoneExt("127", "America", "America/Indiana/Vevay"));
        timeMap.put("128", new TimezoneExt("128", "America", "America/Indiana/Vincennes"));
        timeMap.put("129", new TimezoneExt("129", "America", "America/Indiana/Winamac"));
        timeMap.put("130", new TimezoneExt("130", "America", "America/Indianapolis"));
        timeMap.put("131", new TimezoneExt("131", "America", "America/Iqaluit"));
        timeMap.put("132", new TimezoneExt("132", "America", "America/Jamaica"));
        timeMap.put("133", new TimezoneExt("133", "America", "America/Kentucky/Louisville"));
        timeMap.put("134", new TimezoneExt("134", "America", "America/Kentucky/Monticello"));
        timeMap.put("135", new TimezoneExt("135", "America", "America/Lima"));
        timeMap.put("136", new TimezoneExt("136", "America", "America/Louisville"));
        timeMap.put("137", new TimezoneExt("137", "America", "America/Montreal"));
        timeMap.put("138", new TimezoneExt("138", "America", "America/Nassau"));
        timeMap.put("139", new TimezoneExt("139", "America", "America/New_York"));
        timeMap.put("140", new TimezoneExt("140", "America", "America/Nipigon"));
        timeMap.put("141", new TimezoneExt("141", "America", "America/Panama"));
        timeMap.put("142", new TimezoneExt("142", "America", "America/Pangnirtung"));
        timeMap.put("143", new TimezoneExt("143", "America", "America/Port-au-Prince"));
        timeMap.put("144", new TimezoneExt("144", "America", "America/Thunder_Bay"));
        timeMap.put("145", new TimezoneExt("145", "America", "America/Toronto"));
        timeMap.put("158", new TimezoneExt("158", "America", "America/Caracas"));
        timeMap.put("159", new TimezoneExt("159", "America", "America/Anguilla"));
        timeMap.put("160", new TimezoneExt("160", "America", "America/Antigua"));
        timeMap.put("161", new TimezoneExt("161", "America", "America/Argentina/San_Luis"));
        timeMap.put("162", new TimezoneExt("162", "America", "America/Aruba"));
        timeMap.put("163", new TimezoneExt("163", "America", "America/Asuncion"));
        timeMap.put("164", new TimezoneExt("164", "America", "America/Barbados"));
        timeMap.put("165", new TimezoneExt("165", "America", "America/Blanc-Sablon"));
        timeMap.put("166", new TimezoneExt("166", "America", "America/Boa_Vista"));
        timeMap.put("167", new TimezoneExt("167", "America", "America/Campo_Grande"));
        timeMap.put("168", new TimezoneExt("168", "America", "America/Cuiaba"));
        timeMap.put("169", new TimezoneExt("169", "America", "America/Curacao"));
        timeMap.put("170", new TimezoneExt("170", "America", "America/Dominica"));
        timeMap.put("171", new TimezoneExt("171", "America", "America/Eirunepe"));
        timeMap.put("172", new TimezoneExt("172", "America", "America/Glace_Bay"));
        timeMap.put("173", new TimezoneExt("173", "America", "America/Goose_Bay"));
        timeMap.put("174", new TimezoneExt("174", "America", "America/Grenada"));
        timeMap.put("175", new TimezoneExt("175", "America", "America/Guadeloupe"));
        timeMap.put("176", new TimezoneExt("176", "America", "America/Guyana"));
        timeMap.put("177", new TimezoneExt("177", "America", "America/Halifax"));
        timeMap.put("178", new TimezoneExt("178", "America", "America/Kralendijk"));
        timeMap.put("179", new TimezoneExt("179", "America", "America/La_Paz"));
        timeMap.put("180", new TimezoneExt("180", "America", "America/Lower_Princes"));
        timeMap.put("181", new TimezoneExt("181", "America", "America/Manaus"));
        timeMap.put("182", new TimezoneExt("182", "America", "America/Marigot"));
        timeMap.put("183", new TimezoneExt("183", "America", "America/Martinique"));
        timeMap.put("184", new TimezoneExt("184", "America", "America/Moncton"));
        timeMap.put("185", new TimezoneExt("185", "America", "America/Montserrat"));
        timeMap.put("186", new TimezoneExt("186", "America", "America/Port_of_Spain"));
        timeMap.put("187", new TimezoneExt("187", "America", "America/Porto_Acre"));
        timeMap.put("188", new TimezoneExt("188", "America", "America/Porto_Velho"));
        timeMap.put("189", new TimezoneExt("189", "America", "America/Puerto_Rico"));
        timeMap.put("190", new TimezoneExt("190", "America", "America/Rio_Branco"));
        timeMap.put("191", new TimezoneExt("191", "America", "America/Santiago"));
        timeMap.put("192", new TimezoneExt("192", "America", "America/Santo_Domingo"));
        timeMap.put("193", new TimezoneExt("193", "America", "America/St_Barthelemy"));
        timeMap.put("194", new TimezoneExt("194", "America", "America/St_Kitts"));
        timeMap.put("195", new TimezoneExt("195", "America", "America/St_Lucia"));
        timeMap.put("196", new TimezoneExt("196", "America", "America/St_Thomas"));
        timeMap.put("197", new TimezoneExt("197", "America", "America/St_Vincent"));
        timeMap.put("198", new TimezoneExt("198", "America", "America/Thule"));
        timeMap.put("199", new TimezoneExt("199", "America", "America/Tortola"));
        timeMap.put("200", new TimezoneExt("200", "America", "America/Virgin"));
        timeMap.put("201", new TimezoneExt("201", "Antarctica", "Antarctica/Palmer"));
        timeMap.put("202", new TimezoneExt("202", "Atlantic", "Atlantic/Bermuda"));
        timeMap.put("203", new TimezoneExt("203", "Atlantic", "Atlantic/Stanley"));
        timeMap.put("212", new TimezoneExt("212", "America", "America/St_Johns"));
        timeMap.put("216", new TimezoneExt("216", "America", "America/Araguaina"));
        timeMap.put("217", new TimezoneExt("217", "America", "America/Argentina/Buenos_Aires"));
        timeMap.put("218", new TimezoneExt("218", "America", "America/Argentina/Catamarca"));
        timeMap.put("219", new TimezoneExt("219", "America", "America/Argentina/ComodRivadavia"));
        timeMap.put("220", new TimezoneExt("220", "America", "America/Argentina/Cordoba"));
        timeMap.put("221", new TimezoneExt("221", "America", "America/Argentina/Jujuy"));
        timeMap.put("222", new TimezoneExt("222", "America", "America/Argentina/La_Rioja"));
        timeMap.put("223", new TimezoneExt("223", "America", "America/Argentina/Mendoza"));
        timeMap.put("224", new TimezoneExt("224", "America", "America/Argentina/Rio_Gallegos"));
        timeMap.put("225", new TimezoneExt("225", "America", "America/Argentina/Salta"));
        timeMap.put("226", new TimezoneExt("226", "America", "America/Argentina/San_Juan"));
        timeMap.put("227", new TimezoneExt("227", "America", "America/Argentina/Tucuman"));
        timeMap.put("228", new TimezoneExt("228", "America", "America/Argentina/Ushuaia"));
        timeMap.put("229", new TimezoneExt("229", "America", "America/Bahia"));
        timeMap.put("230", new TimezoneExt("230", "America", "America/Belem"));
        timeMap.put("231", new TimezoneExt("231", "America", "America/Buenos_Aires"));
        timeMap.put("232", new TimezoneExt("232", "America", "America/Catamarca"));
        timeMap.put("233", new TimezoneExt("233", "America", "America/Cayenne"));
        timeMap.put("234", new TimezoneExt("234", "America", "America/Cordoba"));
        timeMap.put("235", new TimezoneExt("235", "America", "America/Fortaleza"));
        timeMap.put("236", new TimezoneExt("236", "America", "America/Godthab"));
        timeMap.put("237", new TimezoneExt("237", "America", "America/Jujuy"));
        timeMap.put("238", new TimezoneExt("238", "America", "America/Maceio"));
        timeMap.put("239", new TimezoneExt("239", "America", "America/Mendoza"));
        timeMap.put("240", new TimezoneExt("240", "America", "America/Miquelon"));
        timeMap.put("241", new TimezoneExt("241", "America", "America/Montevideo"));
        timeMap.put("242", new TimezoneExt("242", "America", "America/Paramaribo"));
        timeMap.put("243", new TimezoneExt("243", "America", "America/Recife"));
        timeMap.put("244", new TimezoneExt("244", "America", "America/Rosario"));
        timeMap.put("245", new TimezoneExt("245", "America", "America/Santarem"));
        timeMap.put("246", new TimezoneExt("246", "America", "America/Sao_Paulo"));
        timeMap.put("247", new TimezoneExt("247", "Antarctica", "Antarctica/Rothera"));
        timeMap.put("251", new TimezoneExt("251", "America", "America/Noronha"));
        timeMap.put("252", new TimezoneExt("252", "Atlantic", "Atlantic/South_Georgia"));
        timeMap.put("255", new TimezoneExt("255", "America", "America/Scoresbysund"));
        timeMap.put("256", new TimezoneExt("256", "Atlantic", "Atlantic/Azores"));
        timeMap.put("257", new TimezoneExt("257", "Atlantic", "Atlantic/Cape_Verde"));
        timeMap.put("259", new TimezoneExt("259", "Africa", "Africa/Abidjan"));
        timeMap.put("260", new TimezoneExt("260", "Africa", "Africa/Accra"));
        timeMap.put("261", new TimezoneExt("261", "Africa", "Africa/Bamako"));
        timeMap.put("262", new TimezoneExt("262", "Africa", "Africa/Banjul"));
        timeMap.put("263", new TimezoneExt("263", "Africa", "Africa/Bissau"));
        timeMap.put("264", new TimezoneExt("264", "Africa", "Africa/Casablanca"));
        timeMap.put("265", new TimezoneExt("265", "Africa", "Africa/Conakry"));
        timeMap.put("266", new TimezoneExt("266", "Africa", "Africa/Dakar"));
        timeMap.put("267", new TimezoneExt("267", "Africa", "Africa/El_Aaiun"));
        timeMap.put("268", new TimezoneExt("268", "Africa", "Africa/Freetown"));
        timeMap.put("269", new TimezoneExt("269", "Africa", "Africa/Lome"));
        timeMap.put("270", new TimezoneExt("270", "Africa", "Africa/Monrovia"));
        timeMap.put("271", new TimezoneExt("271", "Africa", "Africa/Nouakchott"));
        timeMap.put("272", new TimezoneExt("272", "Africa", "Africa/Ouagadougou"));
        timeMap.put("273", new TimezoneExt("273", "Africa", "Africa/Sao_Tome"));
        timeMap.put("274", new TimezoneExt("274", "Africa", "Africa/Timbuktu"));
        timeMap.put("275", new TimezoneExt("275", "America", "America/Danmarkshavn"));
        timeMap.put("276", new TimezoneExt("276", "Atlantic", "Atlantic/Canary"));
        timeMap.put("277", new TimezoneExt("277", "Atlantic", "Atlantic/Faeroe"));
        timeMap.put("278", new TimezoneExt("278", "Atlantic", "Atlantic/Faroe"));
        timeMap.put("279", new TimezoneExt("279", "Atlantic", "Atlantic/Madeira"));
        timeMap.put("280", new TimezoneExt("280", "Atlantic", "Atlantic/Reykjavik"));
        timeMap.put("281", new TimezoneExt("281", "Atlantic", "Atlantic/St_Helena"));
        timeMap.put("289", new TimezoneExt("289", "UTC", "Etc/UTC"));
        timeMap.put("292", new TimezoneExt("292", "Europe", "Europe/Belfast"));
        timeMap.put("293", new TimezoneExt("293", "Europe", "Europe/Dublin"));
        timeMap.put("294", new TimezoneExt("294", "Europe", "Europe/Guernsey"));
        timeMap.put("295", new TimezoneExt("295", "Europe", "Europe/Isle_of_Man"));
        timeMap.put("296", new TimezoneExt("296", "Europe", "Europe/Jersey"));
        timeMap.put("297", new TimezoneExt("297", "Europe", "Europe/Lisbon"));
        timeMap.put("298", new TimezoneExt("298", "Europe", "Europe/London"));
        timeMap.put("311", new TimezoneExt("311", "Africa", "Africa/Algiers"));
        timeMap.put("312", new TimezoneExt("312", "Africa", "Africa/Bangui"));
        timeMap.put("313", new TimezoneExt("313", "Africa", "Africa/Brazzaville"));
        timeMap.put("314", new TimezoneExt("314", "Africa", "Africa/Ceuta"));
        timeMap.put("315", new TimezoneExt("315", "Africa", "Africa/Douala"));
        timeMap.put("316", new TimezoneExt("316", "Africa", "Africa/Kinshasa"));
        timeMap.put("317", new TimezoneExt("317", "Africa", "Africa/Lagos"));
        timeMap.put("318", new TimezoneExt("318", "Africa", "Africa/Libreville"));
        timeMap.put("319", new TimezoneExt("319", "Africa", "Africa/Luanda"));
        timeMap.put("320", new TimezoneExt("320", "Africa", "Africa/Malabo"));
        timeMap.put("321", new TimezoneExt("321", "Africa", "Africa/Ndjamena"));
        timeMap.put("322", new TimezoneExt("322", "Africa", "Africa/Niamey"));
        timeMap.put("323", new TimezoneExt("323", "Africa", "Africa/Porto-Novo"));
        timeMap.put("324", new TimezoneExt("324", "Africa", "Africa/Tunis"));
        timeMap.put("325", new TimezoneExt("325", "Africa", "Africa/Windhoek"));
        timeMap.put("327", new TimezoneExt("327", "Atlantic", "Atlantic/Jan_Mayen"));
        timeMap.put("331", new TimezoneExt("331", "Europe", "Europe/Amsterdam"));
        timeMap.put("332", new TimezoneExt("332", "Europe", "Europe/Andorra"));
        timeMap.put("333", new TimezoneExt("333", "Europe", "Europe/Belgrade"));
        timeMap.put("334", new TimezoneExt("334", "Europe", "Europe/Berlin"));
        timeMap.put("335", new TimezoneExt("335", "Europe", "Europe/Bratislava"));
        timeMap.put("336", new TimezoneExt("336", "Europe", "Europe/Brussels"));
        timeMap.put("337", new TimezoneExt("337", "Europe", "Europe/Budapest"));
        timeMap.put("338", new TimezoneExt("338", "Europe", "Europe/Copenhagen"));
        timeMap.put("339", new TimezoneExt("339", "Europe", "Europe/Gibraltar"));
        timeMap.put("340", new TimezoneExt("340", "Europe", "Europe/Ljubljana"));
        timeMap.put("341", new TimezoneExt("341", "Europe", "Europe/Luxembourg"));
        timeMap.put("342", new TimezoneExt("342", "Europe", "Europe/Madrid"));
        timeMap.put("343", new TimezoneExt("343", "Europe", "Europe/Malta"));
        timeMap.put("344", new TimezoneExt("344", "Europe", "Europe/Monaco"));
        timeMap.put("345", new TimezoneExt("345", "Europe", "Europe/Oslo"));
        timeMap.put("346", new TimezoneExt("346", "Europe", "Europe/Paris"));
        timeMap.put("347", new TimezoneExt("347", "Europe", "Europe/Podgorica"));
        timeMap.put("348", new TimezoneExt("348", "Europe", "Europe/Prague"));
        timeMap.put("349", new TimezoneExt("349", "Europe", "Europe/Rome"));
        timeMap.put("350", new TimezoneExt("350", "Europe", "Europe/San_Marino"));
        timeMap.put("351", new TimezoneExt("351", "Europe", "Europe/Sarajevo"));
        timeMap.put("352", new TimezoneExt("352", "Europe", "Europe/Skopje"));
        timeMap.put("353", new TimezoneExt("353", "Europe", "Europe/Stockholm"));
        timeMap.put("354", new TimezoneExt("354", "Europe", "Europe/Tirane"));
        timeMap.put("355", new TimezoneExt("355", "Europe", "Europe/Vaduz"));
        timeMap.put("356", new TimezoneExt("356", "Europe", "Europe/Vatican"));
        timeMap.put("357", new TimezoneExt("357", "Europe", "Europe/Vienna"));
        timeMap.put("358", new TimezoneExt("358", "Europe", "Europe/Warsaw"));
        timeMap.put("359", new TimezoneExt("359", "Europe", "Europe/Zagreb"));
        timeMap.put("360", new TimezoneExt("360", "Europe", "Europe/Zurich"));
        timeMap.put("364", new TimezoneExt("364", "Africa", "Africa/Blantyre"));
        timeMap.put("365", new TimezoneExt("365", "Africa", "Africa/Bujumbura"));
        timeMap.put("366", new TimezoneExt("366", "Africa", "Africa/Cairo"));
        timeMap.put("367", new TimezoneExt("367", "Africa", "Africa/Gaborone"));
        timeMap.put("368", new TimezoneExt("368", "Africa", "Africa/Harare"));
        timeMap.put("369", new TimezoneExt("369", "Africa", "Africa/Johannesburg"));
        timeMap.put("370", new TimezoneExt("370", "Africa", "Africa/Kigali"));
        timeMap.put("371", new TimezoneExt("371", "Africa", "Africa/Lubumbashi"));
        timeMap.put("372", new TimezoneExt("372", "Africa", "Africa/Lusaka"));
        timeMap.put("373", new TimezoneExt("373", "Africa", "Africa/Maputo"));
        timeMap.put("374", new TimezoneExt("374", "Africa", "Africa/Maseru"));
        timeMap.put("375", new TimezoneExt("375", "Africa", "Africa/Mbabane"));
        timeMap.put("376", new TimezoneExt("376", "Africa", "Africa/Tripoli"));
        timeMap.put("377", new TimezoneExt("377", "Asia", "Asia/Amman"));
        timeMap.put("378", new TimezoneExt("378", "Asia", "Asia/Beirut"));
        timeMap.put("379", new TimezoneExt("379", "Asia", "Asia/Damascus"));
        timeMap.put("380", new TimezoneExt("380", "Asia", "Asia/Gaza"));
        timeMap.put("381", new TimezoneExt("381", "Asia", "Asia/Hebron"));
        timeMap.put("382", new TimezoneExt("382", "Asia", "Asia/Istanbul"));
        timeMap.put("383", new TimezoneExt("383", "Asia", "Asia/Jerusalem"));
        timeMap.put("384", new TimezoneExt("384", "Asia", "Asia/Nicosia"));
        timeMap.put("385", new TimezoneExt("385", "Asia", "Asia/Tel_Aviv"));
        timeMap.put("390", new TimezoneExt("390", "Europe", "Europe/Athens"));
        timeMap.put("391", new TimezoneExt("391", "Europe", "Europe/Bucharest"));
        timeMap.put("392", new TimezoneExt("392", "Europe", "Europe/Chisinau"));
        timeMap.put("393", new TimezoneExt("393", "Europe", "Europe/Helsinki"));
        timeMap.put("394", new TimezoneExt("394", "Europe", "Europe/Istanbul"));
        timeMap.put("395", new TimezoneExt("395", "Europe", "Europe/Mariehamn"));
        timeMap.put("396", new TimezoneExt("396", "Europe", "Europe/Nicosia"));
        timeMap.put("397", new TimezoneExt("397", "Europe", "Europe/Riga"));
        timeMap.put("398", new TimezoneExt("398", "Europe", "Europe/Sofia"));
        timeMap.put("399", new TimezoneExt("399", "Europe", "Europe/Tallinn"));
        timeMap.put("400", new TimezoneExt("400", "Europe", "Europe/Tiraspol"));
        timeMap.put("401", new TimezoneExt("401", "Europe", "Europe/Vilnius"));
        timeMap.put("405", new TimezoneExt("405", "Africa", "Africa/Addis_Ababa"));
        timeMap.put("406", new TimezoneExt("406", "Africa", "Africa/Asmara"));
        timeMap.put("407", new TimezoneExt("407", "Africa", "Africa/Asmera"));
        timeMap.put("408", new TimezoneExt("408", "Africa", "Africa/Dar_es_Salaam"));
        timeMap.put("409", new TimezoneExt("409", "Africa", "Africa/Djibouti"));
        timeMap.put("410", new TimezoneExt("410", "Africa", "Africa/Juba"));
        timeMap.put("411", new TimezoneExt("411", "Africa", "Africa/Kampala"));
        timeMap.put("412", new TimezoneExt("412", "Africa", "Africa/Khartoum"));
        timeMap.put("413", new TimezoneExt("413", "Africa", "Africa/Mogadishu"));
        timeMap.put("414", new TimezoneExt("414", "Africa", "Africa/Nairobi"));
        timeMap.put("415", new TimezoneExt("415", "Antarctica", "Antarctica/Syowa"));
        timeMap.put("416", new TimezoneExt("416", "Asia", "Asia/Aden"));
        timeMap.put("417", new TimezoneExt("417", "Asia", "Asia/Baghdad"));
        timeMap.put("418", new TimezoneExt("418", "Asia", "Asia/Bahrain"));
        timeMap.put("419", new TimezoneExt("419", "Asia", "Asia/Kuwait"));
        timeMap.put("420", new TimezoneExt("420", "Asia", "Asia/Qatar"));
        timeMap.put("421", new TimezoneExt("421", "Asia", "Asia/Riyadh"));
        timeMap.put("424", new TimezoneExt("424", "Europe", "Europe/Kaliningrad"));
        timeMap.put("425", new TimezoneExt("425", "Europe", "Europe/Kiev"));
        timeMap.put("426", new TimezoneExt("426", "Europe", "Europe/Minsk"));
        timeMap.put("427", new TimezoneExt("427", "Europe", "Europe/Simferopol"));
        timeMap.put("428", new TimezoneExt("428", "Europe", "Europe/Uzhgorod"));
        timeMap.put("429", new TimezoneExt("429", "Europe", "Europe/Zaporozhye"));
        timeMap.put("430", new TimezoneExt("430", "Indian", "Indian/Antananarivo"));
        timeMap.put("431", new TimezoneExt("431", "Indian", "Indian/Comoro"));
        timeMap.put("432", new TimezoneExt("432", "Indian", "Indian/Mayotte"));
        timeMap.put("433", new TimezoneExt("433", "Asia", "Asia/Riyadh87"));
        timeMap.put("434", new TimezoneExt("434", "Asia", "Asia/Riyadh88"));
        timeMap.put("435", new TimezoneExt("435", "Asia", "Asia/Riyadh89"));
        timeMap.put("439", new TimezoneExt("439", "Asia", "Asia/Tehran"));
        timeMap.put("441", new TimezoneExt("441", "Asia", "Asia/Baku"));
        timeMap.put("442", new TimezoneExt("442", "Asia", "Asia/Dubai"));
        timeMap.put("443", new TimezoneExt("443", "Asia", "Asia/Muscat"));
        timeMap.put("444", new TimezoneExt("444", "Asia", "Asia/Tbilisi"));
        timeMap.put("445", new TimezoneExt("445", "Asia", "Asia/Yerevan"));
        timeMap.put("447", new TimezoneExt("447", "Europe", "Europe/Moscow"));
        timeMap.put("448", new TimezoneExt("448", "Europe", "Europe/Samara"));
        timeMap.put("449", new TimezoneExt("449", "Europe", "Europe/Volgograd"));
        timeMap.put("450", new TimezoneExt("450", "Indian", "Indian/Mahe"));
        timeMap.put("451", new TimezoneExt("451", "Indian", "Indian/Mauritius"));
        timeMap.put("452", new TimezoneExt("452", "Indian", "Indian/Reunion"));
        timeMap.put("455", new TimezoneExt("455", "Asia", "Asia/Kabul"));
        timeMap.put("456", new TimezoneExt("456", "Antarctica", "Antarctica/Mawson"));
        timeMap.put("457", new TimezoneExt("457", "Asia", "Asia/Aqtau"));
        timeMap.put("458", new TimezoneExt("458", "Asia", "Asia/Aqtobe"));
        timeMap.put("459", new TimezoneExt("459", "Asia", "Asia/Ashgabat"));
        timeMap.put("460", new TimezoneExt("460", "Asia", "Asia/Ashkhabad"));
        timeMap.put("461", new TimezoneExt("461", "Asia", "Asia/Dushanbe"));
        timeMap.put("462", new TimezoneExt("462", "Asia", "Asia/Karachi"));
        timeMap.put("463", new TimezoneExt("463", "Asia", "Asia/Oral"));
        timeMap.put("464", new TimezoneExt("464", "Asia", "Asia/Samarkand"));
        timeMap.put("465", new TimezoneExt("465", "Asia", "Asia/Tashkent"));
        timeMap.put("467", new TimezoneExt("467", "Indian", "Indian/Kerguelen"));
        timeMap.put("468", new TimezoneExt("468", "Indian", "Indian/Maldives"));
        timeMap.put("470", new TimezoneExt("470", "Asia", "Asia/Calcutta"));
        timeMap.put("471", new TimezoneExt("471", "Asia", "Asia/Colombo"));
        timeMap.put("472", new TimezoneExt("472", "Asia", "Asia/Kolkata"));
        timeMap.put("474", new TimezoneExt("474", "Asia", "Asia/Kathmandu"));
        timeMap.put("475", new TimezoneExt("475", "Asia", "Asia/Katmandu"));
        timeMap.put("476", new TimezoneExt("476", "Antarctica", "Antarctica/Vostok"));
        timeMap.put("477", new TimezoneExt("477", "Asia", "Asia/Almaty"));
        timeMap.put("478", new TimezoneExt("478", "Asia", "Asia/Bishkek"));
        timeMap.put("479", new TimezoneExt("479", "Asia", "Asia/Dacca"));
        timeMap.put("480", new TimezoneExt("480", "Asia", "Asia/Dhaka"));
        timeMap.put("481", new TimezoneExt("481", "Asia", "Asia/Qyzylorda"));
        timeMap.put("482", new TimezoneExt("482", "Asia", "Asia/Thimbu"));
        timeMap.put("483", new TimezoneExt("483", "Asia", "Asia/Thimphu"));
        timeMap.put("484", new TimezoneExt("484", "Asia", "Asia/Yekaterinburg"));
        timeMap.put("487", new TimezoneExt("487", "Indian", "Indian/Chagos"));
        timeMap.put("488", new TimezoneExt("488", "Asia", "Asia/Rangoon"));
        timeMap.put("489", new TimezoneExt("489", "Indian", "Indian/Cocos"));
        timeMap.put("490", new TimezoneExt("490", "Antarctica", "Antarctica/Davis"));
        timeMap.put("491", new TimezoneExt("491", "Asia", "Asia/Bangkok"));
        timeMap.put("492", new TimezoneExt("492", "Asia", "Asia/Ho_Chi_Minh"));
        timeMap.put("493", new TimezoneExt("493", "Asia", "Asia/Hovd"));
        timeMap.put("494", new TimezoneExt("494", "Asia", "Asia/Jakarta"));
        timeMap.put("495", new TimezoneExt("495", "Asia", "Asia/Novokuznetsk"));
        timeMap.put("496", new TimezoneExt("496", "Asia", "Asia/Novosibirsk"));
        timeMap.put("497", new TimezoneExt("497", "Asia", "Asia/Omsk"));
        timeMap.put("498", new TimezoneExt("498", "Asia", "Asia/Phnom_Penh"));
        timeMap.put("499", new TimezoneExt("499", "Asia", "Asia/Pontianak"));
        timeMap.put("500", new TimezoneExt("500", "Asia", "Asia/Saigon"));
        timeMap.put("501", new TimezoneExt("501", "Asia", "Asia/Vientiane"));
        timeMap.put("503", new TimezoneExt("503", "Indian", "Indian/Christmas"));
        timeMap.put("505", new TimezoneExt("505", "Antarctica", "Antarctica/Casey"));
        timeMap.put("506", new TimezoneExt("506", "Asia", "Asia/Brunei"));
        timeMap.put("507", new TimezoneExt("507", "Asia", "Asia/Choibalsan"));
        timeMap.put("508", new TimezoneExt("508", "Asia", "Asia/Chongqing"));
        timeMap.put("509", new TimezoneExt("509", "Asia", "Asia/Chungking"));
        timeMap.put("510", new TimezoneExt("510", "Asia", "Asia/Harbin"));
        timeMap.put("511", new TimezoneExt("511", "Asia", "Asia/Hong_Kong"));
        timeMap.put("512", new TimezoneExt("512", "Asia", "Asia/Kashgar"));
        timeMap.put("513", new TimezoneExt("513", "Asia", "Asia/Krasnoyarsk"));
        timeMap.put("514", new TimezoneExt("514", "Asia", "Asia/Kuala_Lumpur"));
        timeMap.put("515", new TimezoneExt("515", "Asia", "Asia/Kuching"));
        timeMap.put("516", new TimezoneExt("516", "Asia", "Asia/Macao"));
        timeMap.put("517", new TimezoneExt("517", "Asia", "Asia/Macau"));
        timeMap.put("518", new TimezoneExt("518", "Asia", "Asia/Makassar"));
        timeMap.put("519", new TimezoneExt("519", "Asia", "Asia/Manila"));
        timeMap.put("520", new TimezoneExt("520", "Asia", "Asia/Shanghai"));
        timeMap.put("521", new TimezoneExt("521", "Asia", "Asia/Singapore"));
        timeMap.put("522", new TimezoneExt("522", "Asia", "Asia/Taipei"));
        timeMap.put("523", new TimezoneExt("523", "Asia", "Asia/Ujung_Pandang"));
        timeMap.put("524", new TimezoneExt("524", "Asia", "Asia/Ulaanbaatar"));
        timeMap.put("525", new TimezoneExt("525", "Asia", "Asia/Ulan_Bator"));
        timeMap.put("526", new TimezoneExt("526", "Asia", "Asia/Urumqi"));
        timeMap.put("527", new TimezoneExt("527", "Australia", "Australia/Perth"));
        timeMap.put("528", new TimezoneExt("528", "Australia", "Australia/West"));
        timeMap.put("534", new TimezoneExt("534", "Australia", "Australia/Eucla"));
        timeMap.put("535", new TimezoneExt("535", "Asia", "Asia/Dili"));
        timeMap.put("536", new TimezoneExt("536", "Asia", "Asia/Irkutsk"));
        timeMap.put("537", new TimezoneExt("537", "Asia", "Asia/Jayapura"));
        timeMap.put("538", new TimezoneExt("538", "Asia", "Asia/Pyongyang"));
        timeMap.put("539", new TimezoneExt("539", "Asia", "Asia/Seoul"));
        timeMap.put("540", new TimezoneExt("540", "Asia", "Asia/Tokyo"));
        timeMap.put("544", new TimezoneExt("544", "Pacific", "Pacific/Palau"));
        timeMap.put("547", new TimezoneExt("547", "Australia", "Australia/Adelaide"));
        timeMap.put("548", new TimezoneExt("548", "Australia", "Australia/Broken_Hill"));
        timeMap.put("549", new TimezoneExt("549", "Australia", "Australia/Darwin"));
        timeMap.put("550", new TimezoneExt("550", "Australia", "Australia/North"));
        timeMap.put("551", new TimezoneExt("551", "Australia", "Australia/South"));
        timeMap.put("552", new TimezoneExt("552", "Australia", "Australia/Yancowinna"));
        timeMap.put("554", new TimezoneExt("554", "Antarctica", "Antarctica/DumontDUrville"));
        timeMap.put("555", new TimezoneExt("555", "Asia", "Asia/Yakutsk"));
        timeMap.put("556", new TimezoneExt("556", "Australia", "Australia/ACT"));
        timeMap.put("557", new TimezoneExt("557", "Australia", "Australia/Brisbane"));
        timeMap.put("558", new TimezoneExt("558", "Australia", "Australia/Canberra"));
        timeMap.put("559", new TimezoneExt("559", "Australia", "Australia/Currie"));
        timeMap.put("560", new TimezoneExt("560", "Australia", "Australia/Hobart"));
        timeMap.put("561", new TimezoneExt("561", "Australia", "Australia/Lindeman"));
        timeMap.put("562", new TimezoneExt("562", "Australia", "Australia/Melbourne"));
        timeMap.put("563", new TimezoneExt("563", "Australia", "Australia/NSW"));
        timeMap.put("564", new TimezoneExt("564", "Australia", "Australia/Queensland"));
        timeMap.put("565", new TimezoneExt("565", "Australia", "Australia/Sydney"));
        timeMap.put("566", new TimezoneExt("566", "Australia", "Australia/Tasmania"));
        timeMap.put("567", new TimezoneExt("567", "Australia", "Australia/Victoria"));
        timeMap.put("569", new TimezoneExt("569", "Pacific", "Pacific/Chuuk"));
        timeMap.put("570", new TimezoneExt("570", "Pacific", "Pacific/Guam"));
        timeMap.put("571", new TimezoneExt("571", "Pacific", "Pacific/Port_Moresby"));
        timeMap.put("572", new TimezoneExt("572", "Pacific", "Pacific/Saipan"));
        timeMap.put("573", new TimezoneExt("573", "Pacific", "Pacific/Truk"));
        timeMap.put("574", new TimezoneExt("574", "Pacific", "Pacific/Yap"));
        timeMap.put("575", new TimezoneExt("575", "Australia", "Australia/LHI"));
        timeMap.put("576", new TimezoneExt("576", "Australia", "Australia/Lord_Howe"));
        timeMap.put("577", new TimezoneExt("577", "Antarctica", "Antarctica/Macquarie"));
        timeMap.put("578", new TimezoneExt("578", "Asia", "Asia/Sakhalin"));
        timeMap.put("579", new TimezoneExt("579", "Asia", "Asia/Vladivostok"));
        timeMap.put("581", new TimezoneExt("581", "Pacific", "Pacific/Efate"));
        timeMap.put("582", new TimezoneExt("582", "Pacific", "Pacific/Guadalcanal"));
        timeMap.put("583", new TimezoneExt("583", "Pacific", "Pacific/Kosrae"));
        timeMap.put("584", new TimezoneExt("584", "Pacific", "Pacific/Noumea"));
        timeMap.put("585", new TimezoneExt("585", "Pacific", "Pacific/Pohnpei"));
        timeMap.put("586", new TimezoneExt("586", "Pacific", "Pacific/Ponape"));
        timeMap.put("588", new TimezoneExt("588", "Pacific", "Pacific/Norfolk"));
        timeMap.put("589", new TimezoneExt("589", "Antarctica", "Antarctica/McMurdo"));
        timeMap.put("590", new TimezoneExt("590", "Antarctica", "Antarctica/South_Pole"));
        timeMap.put("591", new TimezoneExt("591", "Asia", "Asia/Anadyr"));
        timeMap.put("592", new TimezoneExt("592", "Asia", "Asia/Kamchatka"));
        timeMap.put("593", new TimezoneExt("593", "Asia", "Asia/Magadan"));
        timeMap.put("598", new TimezoneExt("598", "Pacific", "Pacific/Auckland"));
        timeMap.put("599", new TimezoneExt("599", "Pacific", "Pacific/Fiji"));
        timeMap.put("600", new TimezoneExt("600", "Pacific", "Pacific/Funafuti"));
        timeMap.put("601", new TimezoneExt("601", "Pacific", "Pacific/Kwajalein"));
        timeMap.put("602", new TimezoneExt("602", "Pacific", "Pacific/Majuro"));
        timeMap.put("603", new TimezoneExt("603", "Pacific", "Pacific/Nauru"));
        timeMap.put("604", new TimezoneExt("604", "Pacific", "Pacific/Tarawa"));
        timeMap.put("605", new TimezoneExt("605", "Pacific", "Pacific/Wake"));
        timeMap.put("606", new TimezoneExt("606", "Pacific", "Pacific/Wallis"));
        timeMap.put("608", new TimezoneExt("608", "Pacific", "Pacific/Chatham"));
        timeMap.put("611", new TimezoneExt("611", "Pacific", "Pacific/Apia"));
        timeMap.put("612", new TimezoneExt("612", "Pacific", "Pacific/Enderbury"));
        timeMap.put("613", new TimezoneExt("613", "Pacific", "Pacific/Tongatapu"));
        timeMap.put("615", new TimezoneExt("615", "Pacific", "Pacific/Kiritimati"));

        timeMap = Collections.unmodifiableMap(timeMap);
    }

    public static TimezoneExt getTimezoneExt(String mycollabId) {
        if (mycollabId == null || mycollabId.equals("")) {
            return timeMap.get("3");
        }
        TimezoneExt result = timeMap.get(mycollabId);
        return (result != null) ? result : timeMap.get("3");
    }

    public static TimeZone getTimezone(String mycollabId) {
        TimezoneExt timeZoneExt;
        if (mycollabId == null || mycollabId.equals("")) {
            timeZoneExt = timeMap.get("3");
        } else {
            timeZoneExt = timeMap.get(mycollabId);
        }

        if (timeZoneExt == null) {
            timeZoneExt = timeMap.get("3");
        }

        return timeZoneExt.getTimezone().toTimeZone();
    }

    public static class TimezoneExt {
        private final String id;
        private final DateTimeZone timezone;
        private final String area;

        TimezoneExt(String id, String area, String javaTimeZoneId) {
            this.id = id;
            this.area = area;
            this.timezone = DateTimeZone.forID(javaTimeZoneId);
        }

        public String getDisplayName(Locale locale) {
            return getOffsetString(timezone) + " " + TimezoneMapper.getArea(timezone.getID());
        }

        public DateTimeZone getTimezone() {
            return timezone;
        }

        public String getArea() {
            return area;
        }

        public String getId() {
            return id;
        }
    }

    private static String getArea(String timeZoneDisplay) {
        String areas[] = new String[]{"Africa", "America", "Antarctica",
                "Asia", "Atlantic", "Australia", "Europe", "Etc", "Indian", "Pacific"};
        if (timeZoneDisplay.indexOf("/") > -1) {
            String area = timeZoneDisplay.substring(0, timeZoneDisplay.indexOf("/"));
            for (String item : areas) {
                if (item.equalsIgnoreCase(area.trim())) {
                    return (area.equalsIgnoreCase("etc")) ? "GMT Offset" : item;
                }
            }
        }
        return "";
    }

    private static String getOffsetString(DateTimeZone timeZone) {
        int offsetInMillis = timeZone.getOffset(new DateTime().getMillis());
        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000),
                Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;
        return offset;
    }


    public static void main(String[] args) {
//        System.out.println(TimeZone.getDefault().getDisplayName());
//        String[] availableIDs = TimeZone.getAvailableIDs();
//        for (int i = 0; i < availableIDs.length; i++) {
//            String timezoneId = availableIDs[i];
//            TimeZone timeZone = TimeZone.getTimeZone(timezoneId);
//
//            if (!getArea(timeZone.getID()).equals("")) {
//                System.out
//                        .println("timeMap.put(\"" + (i + 1)
//                                + "\", new TimezoneExt(\"" + (i + 1) + "\", \""
//                                + getOffsetString(timeZone) + "\",\""
//                                + getArea(timeZone.getID()) + "\"," + "\""
//                                + timeZone.getID() + "\")); "
//                                + timeZone.getRawOffset());
//            }
//        }

        Set<String> zoneIds = DateTimeZone.getAvailableIDs();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("ZZ");

        for (String zoneId : zoneIds) {
            String offset = dateTimeFormatter.withZone(DateTimeZone.forID(zoneId)).print(0);
            String longName = TimeZone.getTimeZone(zoneId).getDisplayName();

            System.out.println("(" + offset + ") " + zoneId + ", " + longName);
        }

    }
}
