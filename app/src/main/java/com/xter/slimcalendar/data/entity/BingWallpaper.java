package com.xter.slimcalendar.data.entity;

import com.litesuits.orm.db.annotation.Table;
import com.xter.support.entity.BaseModelAuto;

/**
 * Created by XTER on 2018/4/9.
 * 必应每日壁纸
 */
@Table("bing_wallpaper")
public class BingWallpaper extends BaseModelAuto {

	/**
	 * title : 自然的天堂
	 * img_1920 : http://api.seqier.com/api/bing/img_1920
	 * description : 勒拿河三角洲野生动物保护区的面积为5530平方英里，是俄罗斯最大的保护区之一。这张勒拿河流域的“伪彩色”图像是由陆地卫星7号拍摄的，图中的颜色是通过捕捉红外线、短波红外和红色波长而来的，光波的结合增强了各种地形特征的视野。保护区是众多重要鸟类物种的家园，各种陆地野生动物也在这里自由自在的生活。
	 * subtitle : 俄罗斯，勒拿河三角洲野生动物保护区
	 * copyright : 位于西伯利亚的勒拿河三角洲野生动物保护区，俄罗斯 (© USGS EROS Data Center/NASA)
	 * date : 20180409
	 * img_1366 : http://api.seqier.com/api/bing/img_1366
	 */

	public String title;
	public String img_1920;
	public String description;
	public String subtitle;
	public String copyright;
	public String date;
	public String img_1366;

	@Override
	public String toString() {
		return "BingWallpaper{" +
				"title='" + title + '\'' +
				", img_1920='" + img_1920 + '\'' +
				", description='" + description + '\'' +
				", subtitle='" + subtitle + '\'' +
				", copyright='" + copyright + '\'' +
				", date='" + date + '\'' +
				", img_1366='" + img_1366 + '\'' +
				'}';
	}
}
