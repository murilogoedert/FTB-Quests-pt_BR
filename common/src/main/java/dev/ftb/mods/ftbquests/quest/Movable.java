package dev.ftb.mods.ftbquests.quest;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author LatvianModder
 */
public interface Movable {
	Chapter getChapter();

	double getX();

	double getY();

	double getWidth();

	double getHeight();

	String getShape();

	@Environment(EnvType.CLIENT)
	void move(Chapter to, double x, double y);

	@Environment(EnvType.CLIENT)
	default void drawMoved(PoseStack matrixStack) {
		QuestShape.get(getShape()).shape.withColor(Color4I.WHITE.withAlpha(30)).draw(matrixStack, 0, 0, 1, 1);
	}
}