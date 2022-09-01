import styled from "styled-components";
import TextEditor from "./TextEditor";

function AskBody({
  setTitle,
  setContent,
  totalTags,
  setAutoSelected,
  editorRef,
}) {
  const addTag = (e) => {
    const Tags = totalTags.slice(); // 그냥 복사

    const filteredTags = Tags.filter((el) => {
      return el.name.includes(e.target.value);
    });
    setAutoSelected(filteredTags);
    if (e.target.value === "") {
      setAutoSelected([]);
    }
  };

  return (
    <Container>
      <Top>
        <div className="subject">Title</div>
        <div className="introduce">
          Be specific and imagine you're asking a question to another person
        </div>
        <input
          className="title"
          placeholder="e.g. is there an R function for finding the index of an element in a vector?"
          onChange={(e) => {
            setTitle(e.target.value);
          }}
        ></input>
      </Top>
      <Body>
        <div className="subject">Body</div>
        <div className="introduce">
          include all the information someone would need to answer your question
        </div>
        <TextEditor setContent={setContent} editorRef={editorRef} />
      </Body>
      <Tag>
        <div className="subject">Tags</div>
        <div className="introduce">
          Add up to 5 tags to describe what your question is about
        </div>
        <input
          className="tag"
          placeholder="e.g. (javascript sql-server database)"
          onKeyUp={addTag}
        ></input>
      </Tag>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  background-color: var(--theme-white);
  padding: 10px;
  width: 800px;
  height: 550px;
  box-shadow: 1px 1px 5px gray;
  .subject {
    padding: 2px;
    font-size: var(--text-font);
    font-weight: bold;
  }
  .introduce {
    font-size: var(--small-font);
    padding: 3px;
  }
`;

const Top = styled.div`
  .title {
    width: 780px;
    height: 30px;
  }
`;

const Body = styled.div``;

const Tag = styled.div`
  .tag {
    width: 780px;
    height: 30px;
  }
`;

export default AskBody;
