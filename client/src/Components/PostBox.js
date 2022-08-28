import styled from "styled-components";

function PostBox() {
  return (
    <Container>
      <Leftside>
        <div className="votes">3321 votes</div>
        <div className="answer">31 answer</div>
      </Leftside>
      <Rightside>
        <Righttop>
          how can i make pre-project perfectly, im afraid i cant!!!
        </Righttop>
        <Rightbottom>
          <div className="tags">javascript</div>
          <div className="tags">java</div>
          <div className="tags">react</div>
          <div className="footer">
            <div className="nickname">Iguwana</div>
            <div className="reputation">029</div>
            <div className="createdAt">2022-08-24 Wed</div>
          </div>
        </Rightbottom>
      </Rightside>
    </Container>
  );
}

const Container = styled.div`
  width: 1032px;
  height: 120px;
  border-top: 1px solid var(--font-color-gray);
  display: flex;
`;

const Leftside = styled.div`
  width: 120px;
  height: 120px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-left: 40px;
  .votes {
    color: var(--font-color-black);
    font-size: var(--text-font);
    font-weight: bold;
    margin-bottom: 8px;
  }
  .answer {
    color: var(--font-color-gray);
    font-size: var(--text-font);
    margin-top: 8px;
  }
`;
const Rightside = styled.div`
  width: 922px;
  height: 120px;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
const Righttop = styled.div`
  color: var(--font-color-blue);
  font-size: var(--header-font);
  margin-bottom: 6px;
  margin-left: 20px;
`;
const Rightbottom = styled.div`
  display: flex;
  margin-top: 6px;
  margin-left: 20px;
  .tags {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 5px;
    padding: 1px 10px;
    height: 20px;
    background-color: var(--theme-button-blue);
    color: var(--font-color-teal);
    border-radius: 3px;
    font-size: var(--small-font);
  }
  .footer {
    display: flex;
    margin-left: 440px;
  }
  .nickname {
    color: var(--font-color-blue);
    margin: 2px;
  }
  .reputation {
    font-weight: bold;
    margin: 2px;
  }
  .createdAt {
    color: var(--font-color-gray);
    margin: 2px;
  }
`;

export default PostBox;
