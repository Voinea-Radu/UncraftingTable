package me.lightdream.uncrafting_table.blocks.UncraftingTable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import me.lightdream.uncrafting_table.UncraftingTable;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class UncraftingTableScreen extends ContainerScreen<UncraftingTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(UncraftingTable.MOD_ID, "textures/gui/uncrafting-table-gui.png");

    public UncraftingTableScreen(UncraftingTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int x, int y) {
        //TODO: Add a title to the GUI
        //this.font.drawString(matrixStack, this.title.getString(), 8F, 7F, 4210752);
        //this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8F, (float)(this.ySize - 96 + 2), 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int x, int y) {
        //TODO: Replace the deprecated use
        GlStateManager.color4f(1F,1F,1F,1F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
