package com.github.steveice10.mc.protocol;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientKeepAlivePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientRequestPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientResourcePackStatusPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientTabCompletePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerActionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerChangeHeldItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerInteractEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerMovementPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPlaceBlockPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerStatePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerSwingArmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerUseItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientCloseWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientCreativeInventoryActionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientEnchantItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientWindowActionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientSpectatePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientSteerBoatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientSteerVehiclePacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientUpdateSignPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientVehicleMovePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerBossBarPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerCombatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerDifficultyPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerDisconnectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerKeepAlivePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPluginMessagePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerResourcePackSendPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerRespawnPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerSetCooldownPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerStatisticsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerSwitchCameraPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerTabCompletePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerTitlePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAnimationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAttachPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityCollectItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityDestroyPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityEquipmentPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityPropertiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRemoveEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntitySetPassengersPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityStatusPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityVelocityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerVehicleMovePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerChangeHeldItemPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerSetExperiencePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerUseBedPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnExpOrbPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnGlobalEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPaintingPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerCloseWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerConfirmTransactionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerOpenWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerSetSlotPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerWindowItemsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerWindowPropertyPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockBreakAnimPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockChangePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerBlockActionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerExplosionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerMapDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerMultiBlockChangePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerNotifyClientPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerOpenTileEntityEditorPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayBuiltinSoundPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnParticlePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUnloadChunkPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTileEntityPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerUpdateTimePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerWorldBorderPacket;
import com.github.steveice10.mc.protocol.packet.login.client.LoginStartPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginDisconnectPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginSetCompressionPacket;
import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.auth.service.AuthenticationService;
import com.github.steveice10.mc.protocol.data.SubProtocol;
import com.github.steveice10.mc.protocol.packet.handshake.client.HandshakePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard.ServerDisplayScoreboardPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard.ServerScoreboardObjectivePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard.ServerTeamPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard.ServerUpdateScorePacket;
import com.github.steveice10.mc.protocol.packet.login.client.EncryptionResponsePacket;
import com.github.steveice10.mc.protocol.packet.login.server.EncryptionRequestPacket;
import com.github.steveice10.mc.protocol.packet.login.server.LoginSuccessPacket;
import com.github.steveice10.mc.protocol.packet.status.client.StatusPingPacket;
import com.github.steveice10.mc.protocol.packet.status.client.StatusQueryPacket;
import com.github.steveice10.mc.protocol.packet.status.server.StatusPongPacket;
import com.github.steveice10.mc.protocol.packet.status.server.StatusResponsePacket;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.crypt.AESEncryption;
import com.github.steveice10.packetlib.crypt.PacketEncryption;
import com.github.steveice10.packetlib.packet.DefaultPacketHeader;
import com.github.steveice10.packetlib.packet.PacketHeader;
import com.github.steveice10.packetlib.packet.PacketProtocol;

import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.UUID;

public class MinecraftProtocol extends PacketProtocol {
    private SubProtocol subProtocol = SubProtocol.HANDSHAKE;
    private PacketHeader header = new DefaultPacketHeader();
    private AESEncryption encrypt;

    private GameProfile profile;
    private String accessToken = "";

    @SuppressWarnings("unused")
    private MinecraftProtocol() {
    }

    public MinecraftProtocol(SubProtocol subProtocol) {
        if (subProtocol != SubProtocol.LOGIN && subProtocol != SubProtocol.STATUS) {
            throw new IllegalArgumentException("Only login and status modes are permitted.");
        }

        this.subProtocol = subProtocol;
        if (subProtocol == SubProtocol.LOGIN) {
            profile = new GameProfile((UUID) null, "Player");
        }
    }

    public MinecraftProtocol(String username) {
        this(SubProtocol.LOGIN);
        profile = new GameProfile((UUID) null, username);
    }

    public MinecraftProtocol(String username, String password) throws RequestException {
        this(username, password, false);
    }

    public MinecraftProtocol(String username, String using, boolean token) throws RequestException {
        this(username, using, token, Proxy.NO_PROXY);
    }

    public MinecraftProtocol(String username, String using, boolean token, Proxy authProxy) throws RequestException {
        this(SubProtocol.LOGIN);
        String clientToken = UUID.randomUUID().toString();
        AuthenticationService auth = new AuthenticationService(clientToken, authProxy);
        auth.setUsername(username);
        if (token) {
            auth.setAccessToken(using);
        } else {
            auth.setPassword(using);
        }

        auth.login();
        profile = auth.getSelectedProfile();
        accessToken = auth.getAccessToken();
    }

    public MinecraftProtocol(GameProfile profile, String accessToken) {
        this(SubProtocol.LOGIN);
        this.profile = profile;
        this.accessToken = accessToken;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getSRVRecordPrefix() {
        return "_minecraft";
    }

    @Override
    public PacketHeader getPacketHeader() {
        return header;
    }

    @Override
    public PacketEncryption getEncryption() {
        return encrypt;
    }

    @Override
    public void newClientSession(Client client, Session session) {
        if (profile != null) {
            session.setFlag(MinecraftConstants.PROFILE_KEY, profile);
            session.setFlag(MinecraftConstants.ACCESS_TOKEN_KEY, accessToken);
        }

        setSubProtocol(subProtocol, true, session);
        session.addListener(new ClientListener());
    }

    @Override
    public void newServerSession(Server server, Session session) {
        setSubProtocol(SubProtocol.HANDSHAKE, false, session);
        session.addListener(new ServerListener());
    }

    protected void enableEncryption(Key key) {
        try {
            encrypt = new AESEncryption(key);
        } catch (GeneralSecurityException e) {
            throw new Error("Failed to enable protocol encryption.", e);
        }
    }

    public SubProtocol getSubProtocol() {
        return subProtocol;
    }

    protected void setSubProtocol(SubProtocol subProtocol, boolean client, Session session) {
        clearPackets();
        switch (subProtocol) {
            case HANDSHAKE:
                if (client) {
                    initClientHandshake(session);
                } else {
                    initServerHandshake(session);
                }

                break;
            case LOGIN:
                if (client) {
                    initClientLogin(session);
                } else {
                    initServerLogin(session);
                }

                break;
            case GAME:
                if (client) {
                    initClientGame(session);
                } else {
                    initServerGame(session);
                }

                break;
            case STATUS:
                if (client) {
                    initClientStatus(session);
                } else {
                    initServerStatus(session);
                }

                break;
        }

        this.subProtocol = subProtocol;
    }

    private void initClientHandshake(Session session) {
        registerOutgoing(0, HandshakePacket.class);
    }

    private void initServerHandshake(Session session) {
        registerIncoming(0, HandshakePacket.class);
    }

    private void initClientLogin(Session session) {
        registerIncoming(0x00, LoginDisconnectPacket.class);
        registerIncoming(0x01, EncryptionRequestPacket.class);
        registerIncoming(0x02, LoginSuccessPacket.class);
        registerIncoming(0x03, LoginSetCompressionPacket.class);

        registerOutgoing(0x00, LoginStartPacket.class);
        registerOutgoing(0x01, EncryptionResponsePacket.class);
    }

    private void initServerLogin(Session session) {
        registerIncoming(0x00, LoginStartPacket.class);
        registerIncoming(0x01, EncryptionResponsePacket.class);

        registerOutgoing(0x00, LoginDisconnectPacket.class);
        registerOutgoing(0x01, EncryptionRequestPacket.class);
        registerOutgoing(0x02, LoginSuccessPacket.class);
        registerOutgoing(0x03, LoginSetCompressionPacket.class);
    }

    private void initClientGame(Session session) {
        registerIncoming(0x00, ServerSpawnObjectPacket.class);
        registerIncoming(0x01, ServerSpawnExpOrbPacket.class);
        registerIncoming(0x02, ServerSpawnGlobalEntityPacket.class);
        registerIncoming(0x03, ServerSpawnMobPacket.class);
        registerIncoming(0x04, ServerSpawnPaintingPacket.class);
        registerIncoming(0x05, ServerSpawnPlayerPacket.class);
        registerIncoming(0x06, ServerEntityAnimationPacket.class);
        registerIncoming(0x07, ServerStatisticsPacket.class);
        registerIncoming(0x08, ServerBlockBreakAnimPacket.class);
        registerIncoming(0x09, ServerUpdateTileEntityPacket.class);
        registerIncoming(0x0A, ServerBlockActionPacket.class);
        registerIncoming(0x0B, ServerBlockChangePacket.class);
        registerIncoming(0x0C, ServerBossBarPacket.class);
        registerIncoming(0x0D, ServerDifficultyPacket.class);
        registerIncoming(0x0E, ServerTabCompletePacket.class);
        registerIncoming(0x0F, ServerChatPacket.class);
        registerIncoming(0x10, ServerMultiBlockChangePacket.class);
        registerIncoming(0x11, ServerConfirmTransactionPacket.class);
        registerIncoming(0x12, ServerCloseWindowPacket.class);
        registerIncoming(0x13, ServerOpenWindowPacket.class);
        registerIncoming(0x14, ServerWindowItemsPacket.class);
        registerIncoming(0x15, ServerWindowPropertyPacket.class);
        registerIncoming(0x16, ServerSetSlotPacket.class);
        registerIncoming(0x17, ServerSetCooldownPacket.class);
        registerIncoming(0x18, ServerPluginMessagePacket.class);
        registerIncoming(0x19, ServerPlaySoundPacket.class);
        registerIncoming(0x1A, ServerDisconnectPacket.class);
        registerIncoming(0x1B, ServerEntityStatusPacket.class);
        registerIncoming(0x1C, ServerExplosionPacket.class);
        registerIncoming(0x1D, ServerUnloadChunkPacket.class);
        registerIncoming(0x1E, ServerNotifyClientPacket.class);
        registerIncoming(0x1F, ServerKeepAlivePacket.class);
        registerIncoming(0x20, ServerChunkDataPacket.class);
        registerIncoming(0x21, ServerPlayEffectPacket.class);
        registerIncoming(0x22, ServerSpawnParticlePacket.class);
        registerIncoming(0x23, ServerJoinGamePacket.class);
        registerIncoming(0x24, ServerMapDataPacket.class);
        registerIncoming(0x25, ServerEntityPositionPacket.class);
        registerIncoming(0x26, ServerEntityPositionRotationPacket.class);
        registerIncoming(0x27, ServerEntityRotationPacket.class);
        registerIncoming(0x28, ServerEntityPacket.class);
        registerIncoming(0x29, ServerVehicleMovePacket.class);
        registerIncoming(0x2A, ServerOpenTileEntityEditorPacket.class);
        registerIncoming(0x2B, ServerPlayerAbilitiesPacket.class);
        registerIncoming(0x2C, ServerCombatPacket.class);
        registerIncoming(0x2D, ServerPlayerListEntryPacket.class);
        registerIncoming(0x2E, ServerPlayerPositionRotationPacket.class);
        registerIncoming(0x2F, ServerPlayerUseBedPacket.class);
        registerIncoming(0x30, ServerEntityDestroyPacket.class);
        registerIncoming(0x31, ServerEntityRemoveEffectPacket.class);
        registerIncoming(0x32, ServerResourcePackSendPacket.class);
        registerIncoming(0x33, ServerRespawnPacket.class);
        registerIncoming(0x34, ServerEntityHeadLookPacket.class);
        registerIncoming(0x35, ServerWorldBorderPacket.class);
        registerIncoming(0x36, ServerSwitchCameraPacket.class);
        registerIncoming(0x37, ServerPlayerChangeHeldItemPacket.class);
        registerIncoming(0x38, ServerDisplayScoreboardPacket.class);
        registerIncoming(0x39, ServerEntityMetadataPacket.class);
        registerIncoming(0x3A, ServerEntityAttachPacket.class);
        registerIncoming(0x3B, ServerEntityVelocityPacket.class);
        registerIncoming(0x3C, ServerEntityEquipmentPacket.class);
        registerIncoming(0x3D, ServerPlayerSetExperiencePacket.class);
        registerIncoming(0x3E, ServerPlayerHealthPacket.class);
        registerIncoming(0x3F, ServerScoreboardObjectivePacket.class);
        registerIncoming(0x40, ServerEntitySetPassengersPacket.class);
        registerIncoming(0x41, ServerTeamPacket.class);
        registerIncoming(0x42, ServerUpdateScorePacket.class);
        registerIncoming(0x43, ServerSpawnPositionPacket.class);
        registerIncoming(0x44, ServerUpdateTimePacket.class);
        registerIncoming(0x45, ServerTitlePacket.class);
        registerIncoming(0x46, ServerPlayBuiltinSoundPacket.class);
        registerIncoming(0x47, ServerPlayerListDataPacket.class);
        registerIncoming(0x48, ServerEntityCollectItemPacket.class);
        registerIncoming(0x49, ServerEntityTeleportPacket.class);
        registerIncoming(0x4A, ServerEntityPropertiesPacket.class);
        registerIncoming(0x4B, ServerEntityEffectPacket.class);

        registerOutgoing(0x00, ClientTeleportConfirmPacket.class);
        registerOutgoing(0x01, ClientTabCompletePacket.class);
        registerOutgoing(0x02, ClientChatPacket.class);
        registerOutgoing(0x03, ClientRequestPacket.class);
        registerOutgoing(0x04, ClientSettingsPacket.class);
        registerOutgoing(0x05, ClientConfirmTransactionPacket.class);
        registerOutgoing(0x06, ClientEnchantItemPacket.class);
        registerOutgoing(0x07, ClientWindowActionPacket.class);
        registerOutgoing(0x08, ClientCloseWindowPacket.class);
        registerOutgoing(0x09, ClientPluginMessagePacket.class);
        registerOutgoing(0x0A, ClientPlayerInteractEntityPacket.class);
        registerOutgoing(0x0B, ClientKeepAlivePacket.class);
        registerOutgoing(0x0C, ClientPlayerPositionPacket.class);
        registerOutgoing(0x0D, ClientPlayerPositionRotationPacket.class);
        registerOutgoing(0x0E, ClientPlayerRotationPacket.class);
        registerOutgoing(0x0F, ClientPlayerMovementPacket.class);
        registerOutgoing(0x10, ClientVehicleMovePacket.class);
        registerOutgoing(0x11, ClientSteerBoatPacket.class);
        registerOutgoing(0x12, ClientPlayerAbilitiesPacket.class);
        registerOutgoing(0x13, ClientPlayerActionPacket.class);
        registerOutgoing(0x14, ClientPlayerStatePacket.class);
        registerOutgoing(0x15, ClientSteerVehiclePacket.class);
        registerOutgoing(0x16, ClientResourcePackStatusPacket.class);
        registerOutgoing(0x17, ClientPlayerChangeHeldItemPacket.class);
        registerOutgoing(0x18, ClientCreativeInventoryActionPacket.class);
        registerOutgoing(0x19, ClientUpdateSignPacket.class);
        registerOutgoing(0x1A, ClientPlayerSwingArmPacket.class);
        registerOutgoing(0x1B, ClientSpectatePacket.class);
        registerOutgoing(0x1C, ClientPlayerPlaceBlockPacket.class);
        registerOutgoing(0x1D, ClientPlayerUseItemPacket.class);
    }

    private void initServerGame(Session session) {
        registerIncoming(0x00, ClientTeleportConfirmPacket.class);
        registerIncoming(0x01, ClientTabCompletePacket.class);
        registerIncoming(0x02, ClientChatPacket.class);
        registerIncoming(0x03, ClientRequestPacket.class);
        registerIncoming(0x04, ClientSettingsPacket.class);
        registerIncoming(0x05, ClientConfirmTransactionPacket.class);
        registerIncoming(0x06, ClientEnchantItemPacket.class);
        registerIncoming(0x07, ClientWindowActionPacket.class);
        registerIncoming(0x08, ClientCloseWindowPacket.class);
        registerIncoming(0x09, ClientPluginMessagePacket.class);
        registerIncoming(0x0A, ClientPlayerInteractEntityPacket.class);
        registerIncoming(0x0B, ClientKeepAlivePacket.class);
        registerIncoming(0x0C, ClientPlayerPositionPacket.class);
        registerIncoming(0x0D, ClientPlayerPositionRotationPacket.class);
        registerIncoming(0x0E, ClientPlayerRotationPacket.class);
        registerIncoming(0x0F, ClientPlayerMovementPacket.class);
        registerIncoming(0x10, ClientVehicleMovePacket.class);
        registerIncoming(0x11, ClientSteerBoatPacket.class);
        registerIncoming(0x12, ClientPlayerAbilitiesPacket.class);
        registerIncoming(0x13, ClientPlayerActionPacket.class);
        registerIncoming(0x14, ClientPlayerStatePacket.class);
        registerIncoming(0x15, ClientSteerVehiclePacket.class);
        registerIncoming(0x16, ClientResourcePackStatusPacket.class);
        registerIncoming(0x17, ClientPlayerChangeHeldItemPacket.class);
        registerIncoming(0x18, ClientCreativeInventoryActionPacket.class);
        registerIncoming(0x19, ClientUpdateSignPacket.class);
        registerIncoming(0x1A, ClientPlayerSwingArmPacket.class);
        registerIncoming(0x1B, ClientSpectatePacket.class);
        registerIncoming(0x1C, ClientPlayerPlaceBlockPacket.class);
        registerIncoming(0x1D, ClientPlayerUseItemPacket.class);

        registerOutgoing(0x00, ServerSpawnObjectPacket.class);
        registerOutgoing(0x01, ServerSpawnExpOrbPacket.class);
        registerOutgoing(0x02, ServerSpawnGlobalEntityPacket.class);
        registerOutgoing(0x03, ServerSpawnMobPacket.class);
        registerOutgoing(0x04, ServerSpawnPaintingPacket.class);
        registerOutgoing(0x05, ServerSpawnPlayerPacket.class);
        registerOutgoing(0x06, ServerEntityAnimationPacket.class);
        registerOutgoing(0x07, ServerStatisticsPacket.class);
        registerOutgoing(0x08, ServerBlockBreakAnimPacket.class);
        registerOutgoing(0x09, ServerUpdateTileEntityPacket.class);
        registerOutgoing(0x0A, ServerBlockActionPacket.class);
        registerOutgoing(0x0B, ServerBlockChangePacket.class);
        registerOutgoing(0x0C, ServerBossBarPacket.class);
        registerOutgoing(0x0D, ServerDifficultyPacket.class);
        registerOutgoing(0x0E, ServerTabCompletePacket.class);
        registerOutgoing(0x0F, ServerChatPacket.class);
        registerOutgoing(0x10, ServerMultiBlockChangePacket.class);
        registerOutgoing(0x11, ServerConfirmTransactionPacket.class);
        registerOutgoing(0x12, ServerCloseWindowPacket.class);
        registerOutgoing(0x13, ServerOpenWindowPacket.class);
        registerOutgoing(0x14, ServerWindowItemsPacket.class);
        registerOutgoing(0x15, ServerWindowPropertyPacket.class);
        registerOutgoing(0x16, ServerSetSlotPacket.class);
        registerOutgoing(0x17, ServerSetCooldownPacket.class);
        registerOutgoing(0x18, ServerPluginMessagePacket.class);
        registerOutgoing(0x19, ServerPlaySoundPacket.class);
        registerOutgoing(0x1A, ServerDisconnectPacket.class);
        registerOutgoing(0x1B, ServerEntityStatusPacket.class);
        registerOutgoing(0x1C, ServerExplosionPacket.class);
        registerOutgoing(0x1D, ServerUnloadChunkPacket.class);
        registerOutgoing(0x1E, ServerNotifyClientPacket.class);
        registerOutgoing(0x1F, ServerKeepAlivePacket.class);
        registerOutgoing(0x20, ServerChunkDataPacket.class);
        registerOutgoing(0x21, ServerPlayEffectPacket.class);
        registerOutgoing(0x22, ServerSpawnParticlePacket.class);
        registerOutgoing(0x23, ServerJoinGamePacket.class);
        registerOutgoing(0x24, ServerMapDataPacket.class);
        registerOutgoing(0x25, ServerEntityPositionPacket.class);
        registerOutgoing(0x26, ServerEntityPositionRotationPacket.class);
        registerOutgoing(0x27, ServerEntityRotationPacket.class);
        registerOutgoing(0x28, ServerEntityPacket.class);
        registerOutgoing(0x29, ServerVehicleMovePacket.class);
        registerOutgoing(0x2A, ServerOpenTileEntityEditorPacket.class);
        registerOutgoing(0x2B, ServerPlayerAbilitiesPacket.class);
        registerOutgoing(0x2C, ServerCombatPacket.class);
        registerOutgoing(0x2D, ServerPlayerListEntryPacket.class);
        registerOutgoing(0x2E, ServerPlayerPositionRotationPacket.class);
        registerOutgoing(0x2F, ServerPlayerUseBedPacket.class);
        registerOutgoing(0x30, ServerEntityDestroyPacket.class);
        registerOutgoing(0x31, ServerEntityRemoveEffectPacket.class);
        registerOutgoing(0x32, ServerResourcePackSendPacket.class);
        registerOutgoing(0x33, ServerRespawnPacket.class);
        registerOutgoing(0x34, ServerEntityHeadLookPacket.class);
        registerOutgoing(0x35, ServerWorldBorderPacket.class);
        registerOutgoing(0x36, ServerSwitchCameraPacket.class);
        registerOutgoing(0x37, ServerPlayerChangeHeldItemPacket.class);
        registerOutgoing(0x38, ServerDisplayScoreboardPacket.class);
        registerOutgoing(0x39, ServerEntityMetadataPacket.class);
        registerOutgoing(0x3A, ServerEntityAttachPacket.class);
        registerOutgoing(0x3B, ServerEntityVelocityPacket.class);
        registerOutgoing(0x3C, ServerEntityEquipmentPacket.class);
        registerOutgoing(0x3D, ServerPlayerSetExperiencePacket.class);
        registerOutgoing(0x3E, ServerPlayerHealthPacket.class);
        registerOutgoing(0x3F, ServerScoreboardObjectivePacket.class);
        registerOutgoing(0x40, ServerEntitySetPassengersPacket.class);
        registerOutgoing(0x41, ServerTeamPacket.class);
        registerOutgoing(0x42, ServerUpdateScorePacket.class);
        registerOutgoing(0x43, ServerSpawnPositionPacket.class);
        registerOutgoing(0x44, ServerUpdateTimePacket.class);
        registerOutgoing(0x45, ServerTitlePacket.class);
        registerOutgoing(0x46, ServerPlayBuiltinSoundPacket.class);
        registerOutgoing(0x47, ServerPlayerListDataPacket.class);
        registerOutgoing(0x48, ServerEntityCollectItemPacket.class);
        registerOutgoing(0x49, ServerEntityTeleportPacket.class);
        registerOutgoing(0x4A, ServerEntityPropertiesPacket.class);
        registerOutgoing(0x4B, ServerEntityEffectPacket.class);
    }

    private void initClientStatus(Session session) {
        registerIncoming(0x00, StatusResponsePacket.class);
        registerIncoming(0x01, StatusPongPacket.class);

        registerOutgoing(0x00, StatusQueryPacket.class);
        registerOutgoing(0x01, StatusPingPacket.class);
    }

    private void initServerStatus(Session session) {
        registerIncoming(0x00, StatusQueryPacket.class);
        registerIncoming(0x01, StatusPingPacket.class);

        registerOutgoing(0x00, StatusResponsePacket.class);
        registerOutgoing(0x01, StatusPongPacket.class);
    }
}
