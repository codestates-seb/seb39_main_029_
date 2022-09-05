import styled from "styled-components";
import { Link } from "react-router-dom";

function PostBox({ post }) {
  const createdAt = String(post.createAt).substring(0, 10);

  return (
    <Container>
      <Leftside>
        <span className="votes">{post.votes} votes</span>
        <span className="answer">{post.commentsCount} answer</span>
      </Leftside>
      <Rightside>
        <Righttop>
          <Link
            className="link"
            to={{
              pathname: `/qna/${post.postId}`,
            }}
          >
            {post.subject}
          </Link>
        </Righttop>
        <Rightbottom>
          <span>
            {post.postTag.map((el, idx) => {
              return (
                <span className="tags" key={idx}>
                  {el.name}
                </span>
              );
            })}
          </span>
          <span className="footer">
            <span className="nickname">{post.nickName}</span>
            <span className="reputation">{post.reputation}</span>
            <span className="createdAt">{createdAt}</span>
          </span>
        </Rightbottom>
      </Rightside>
    </Container>
  );
}

const Container = styled.div`
  border-bottom: 1px solid hsl(210, 8%, 85%);
  display: flex;
  height: 110px;
  padding-left: 30px;
`;

const Leftside = styled.div`
  width: 100px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  padding: 10px;
  .votes {
    color: var(--font-color-black);
    font-size: var(--text-font);
    font-weight: bold;
    margin: 0 0 10px 0;
  }
  .answer {
    color: var(--font-color-gray);
    font-size: var(--text-font);
  }
`;
const Rightside = styled.div`
  width: 100%;
  padding: 20px;
`;
const Righttop = styled.div`
  color: var(--font-color-blue);
  font-size: var(--content-font);
  font-weight: 500;
  padding: 10px;
  .link {
    text-decoration-line: none;
    color: var(--font-color-blue);
    font-size: var(--content-font);
    font-weight: 500;
  }
`;
const Rightbottom = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  .tags {
    margin: 0 5px 0 5px;
    padding: 5px 10px;
    height: 20px;
    background-color: var(--theme-button-blue);
    color: var(--font-color-teal);
    border-radius: 0.1rem;
    font-size: var(--small-font);
  }
  .footer {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .nickname {
    color: var(--font-color-blue);
    margin: 0 10px 0 10px;
  }
  .reputation {
    font-weight: bold;
    margin: 0 10px 0 10px;
  }
  .createdAt {
    color: var(--font-color-gray);
    margin: 0 10px 0 10px;
  }
`;

export default PostBox;
