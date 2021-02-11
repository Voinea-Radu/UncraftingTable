package me.lightdream.uncrafting_table.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import me.lightdream.uncrafting_table.UncraftingTable;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class UncraftingTableScreen extends ContainerScreen<UncraftingTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(UncraftingTable.MOD_ID, "textures/gui/uncrafting-table-gui.png");

    public UncraftingTableScreen(UncraftingTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        //this.font.drawString(matrixStack, this.title.getString(), 8F, 7F, 4210752);
        //this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8F, (float)(this.ySize - 96 + 2), 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        GlStateManager.color4f(1F,1F,1F,1F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
