package com.cardparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FileRead {

	public void parseData() {
		try {
			FileReader fileReader = new FileReader("input//file.json");
			BufferedReader br = new BufferedReader(fileReader);
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line.trim());
				line = br.readLine();
			}
			br.close();
			String jsonString = sb.toString();
			JsonElement jelement = new JsonParser().parse(jsonString);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("SurfaceVisibleCardList");

			for (int i = 0; i < jarray.size(); i++) {
				JsonElement obj = jarray.get(i);
				String cardId = obj.getAsJsonObject().get("cardId").getAsString();
				JsonObject factCard = obj.getAsJsonObject().get("factCard").getAsJsonObject();
				String pathToImage = factCard.getAsJsonObject().get("pathToImg").getAsString();
				int topY = factCard.getAsJsonObject().get("topY").getAsInt();
				int topX = factCard.getAsJsonObject().get("topX").getAsInt();
				int width = factCard.getAsJsonObject().get("cardWidth").getAsInt();
				int height = factCard.getAsJsonObject().get("cardHeight").getAsInt();
				String cardType = factCard.getAsJsonObject().get("cardType").getAsString();
				JsonObject dimen = factCard.getAsJsonObject().get("cardPicRect").getAsJsonObject();

				int btm = dimen.getAsJsonObject().get("bottom").getAsInt();
				int top = dimen.getAsJsonObject().get("top").getAsInt();
				int left = dimen.getAsJsonObject().get("left").getAsInt();
				int right = dimen.getAsJsonObject().get("right").getAsInt();

				int imWidth = Math.abs(left - right);
				int imHeight = Math.abs(top - btm);

				System.out.println("Id: " + cardId + " path: " + pathToImage + " Y: " + topY + " X: " + topX
						+ " width: " + width + " height: " + height + " Type: " + cardType + " img width: " + imWidth
						+ " img height: " + imHeight);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
