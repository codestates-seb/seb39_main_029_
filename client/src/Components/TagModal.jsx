import styled from "styled-components";

function TagModal({ autoSeleted, totalTags, setSelectedTag, selectedTag }) {
  const addSelect = (e) => {
    const tagName = e.target.textContent;
    const tag = totalTags.find((t) => t.name === tagName);
    console.log(tagName, tag);
    setSelectedTag([...selectedTag, { tagId: tag.tagsId }]);
  };

  return (
    <Container>
      {autoSeleted.map((el, i) => {
        return (
          <div key={i} className="tag" onClick={addSelect}>
            {el.name}
          </div>
        );
      })}
    </Container>
  );
}

const Container = styled.div`
  background-color: white;
  width: 865px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  .tag {
    background-color: black;
    color: white;
    width: 50px;
    height: 50px;
  }
`;

export default TagModal;
