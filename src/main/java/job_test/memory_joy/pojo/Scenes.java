package job_test.memory_joy.pojo;

public class Scenes {
    private Integer sceneId;

    private String sceneName;

    private Boolean isHide;

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public Boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(Boolean isHide) {
        this.isHide = isHide;
    }

	@Override
	public String toString() {
		return "Scenes [sceneId=" + sceneId + ", sceneName=" + sceneName + ", isHide=" + isHide + "]";
	}
    
    
}