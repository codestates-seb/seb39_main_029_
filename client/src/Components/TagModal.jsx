import styled from "styled-components";
import ColorButton from "../Assets/ColorBtn";

function TagModal({
  autoSeleted,
  totalTags,
  setSelectedTag,
  selectedTag,
  setTagsView,
  tagsView,
}) {
  const addSelect = (e) => {
    const tagName = e.target.textContent;
    const tag = totalTags.find((t) => t.name === tagName);
    setSelectedTag([...selectedTag, { tagId: tag.tagsId }]);
    setTagsView([...tagsView, tag.name]);
  };

  return (
    <Container>
      {autoSeleted.map((el, i) => {
        return (
          <ColorButton
            mode="GREY"
            key={i}
            className="tag"
            onClick={addSelect}
            text={el.name}
          ></ColorButton>
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  margin: 0px 790px 0px 0px;
  background-color: white;
  gap: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  .tag {
    width: 50px;
    height: 50px;
  }
`;

export default TagModal;
